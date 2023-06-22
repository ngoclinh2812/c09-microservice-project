package com.codegym.controller;

import com.codegym.dto.importDto.request.ImportOrderRequestDto;
import com.codegym.dto.importDto.response.OrderConfirmResponseDto;
import com.codegym.dto.importDto.response.TruckDto;
import com.codegym.entity.importEntity.ImportOrder;
import com.codegym.entity.importEntity.Truck;
import com.codegym.model.importModel.request.OrderCompleteRequestModel;
import com.codegym.service.importService.ImportOrderServiceImpl;
import com.codegym.entity.importEntity.ImportOrderDetails;
import com.codegym.service.importService.ImportTruckService;
import com.codegym.utils.ImportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/import")
public class ImportOrderController {

    @Autowired
    private ImportOrderServiceImpl importOrderService;

    @Autowired
    private ImportTruckService importTruckService;

    @Autowired
    private ImportMapper mapper;


    // (1) Gửi request đơn hàng đến máy anh Hải
    @PostMapping("/order")
    public ResponseEntity<ImportOrderDetails> sendOrder(@RequestBody ImportOrderRequestDto orderDto) {

        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<ImportOrderRequestDto> entity = new HttpEntity<ImportOrderRequestDto>(orderDto);

        ResponseEntity<ImportOrderRequestDto> orderResponseEntity = restTemplate.exchange("http://192.168.4.118:8080/api/order",
                HttpMethod.POST, entity, ImportOrderRequestDto.class);

        if (orderResponseEntity.getStatusCode() == HttpStatus.CREATED) {

            ImportOrder order = new ImportOrder();
            Long id = orderResponseEntity.getBody().getId();
            order.setId(id);
            order.setStatus("Dang gui don hang.");

            importOrderService.save(order);

            // Trả về status.OK
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

//    (2) Hứng xác nhận đơn hàng từ phía anh Hải
    @PostMapping("/confirmation")
    public ResponseEntity<?> receiveConfirmationNote(@RequestBody OrderConfirmResponseDto orderResponse) {

        if (orderResponse.getId()!=null) {
            ImportOrder order = importOrderService.findById(orderResponse.getId());
            order.setTotalPrice(orderResponse.getTotalPrice());
            order.setDeliveryDate(orderResponse.getDelivery());

            List<TruckDto> truckDtoList = orderResponse.getTransportationModels();
            List<Truck> truckList = mapper.convertListTruckDtoToListTruckEntity(truckDtoList);
            for (Truck truck : truckList) {
                truck.setOrder(order);
            }
            importTruckService.saveAll(truckList);

            order.setTruckList(truckList);
            order.setStatus("Dang nhan hang.");

            importOrderService.save(order);

            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

//    (3) Xác nhận đã lấy được hàng
    @PostMapping("/complete")
    public ResponseEntity<?> confirmReceipt(@RequestBody OrderCompleteRequestModel orderCompletedResponse) {

        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<OrderCompleteRequestModel> entity = new HttpEntity<OrderCompleteRequestModel>(orderCompletedResponse);

        ResponseEntity<OrderCompleteRequestModel> orderCompletedResponseEntity = restTemplate.exchange("http://192.168.4.118:8080/api/verified",
                HttpMethod.POST, entity, OrderCompleteRequestModel.class);

        if (orderCompletedResponseEntity.getStatusCode() == HttpStatus.OK) {
            ImportOrder order = importOrderService.findById(orderCompletedResponse.getId());
            if (order != null) {
                order.setStatus("Da nhan hang.");
                importOrderService.save(order);
                return new ResponseEntity<String>("OK", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}

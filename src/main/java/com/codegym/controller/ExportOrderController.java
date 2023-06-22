package com.codegym.controller;


import com.codegym.dto.exportDto.request.ExportOrderDetailRequestDto;
import com.codegym.dto.exportDto.request.ExportOrderRequestDto;
import com.codegym.dto.exportDto.response.ExportOrderConfirmDto;
import com.codegym.entity.exportEntity.ExportOrder;
import com.codegym.entity.exportEntity.ExportOrderDetails;
import com.codegym.model.exportModel.request.ExportOrderConfirmModel;
import com.codegym.service.exportService.ExportOrderDetailsService;
import com.codegym.service.exportService.ExportOrderService;
import com.codegym.utils.ExportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/export")
public class ExportOrderController {

    @Autowired
    private ExportOrderService exportOrderService;

    @Autowired
    private ExportOrderDetailsService exportOrderDetailsService;

    @Autowired
    private ExportMapper exportMapper;

    // (1) Hứng dữ liệu từ phía anh Tùng
    @PostMapping("/received")
    public ResponseEntity<?> saveOrder(@RequestBody ExportOrderRequestDto requestDto) {

        ExportOrder exportOrder = new ExportOrder();

        exportOrder.setId(requestDto.getOrderId());
        exportOrderService.save(exportOrder);

        List<ExportOrderDetailRequestDto> dtoList = requestDto.getProductItemListDto();
        List<ExportOrderDetails> entityList = exportMapper.convertListDetailsDtoToListDetailEntity(dtoList);

        for (ExportOrderDetails orderDetails : entityList) {
            orderDetails.setOrder(exportOrder);
        }

        exportOrderDetailsService.saveAll(entityList);

        exportOrder.setOrderDetailsList(entityList);
        exportOrder.setStatus("Dang cho xu ly.");
        exportOrderService.save(exportOrder);

        return new ResponseEntity<>("Ok anh Tung nhe!", HttpStatus.OK);
    }

    // (2) Gửi confirm đến máy anh Tùng
    @PostMapping("/confirm")
    public ResponseEntity<?> sendTest(@RequestBody ExportOrderConfirmModel orderModel) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ExportOrderConfirmModel> entity = new HttpEntity<ExportOrderConfirmModel>(orderModel);

        // Gửi request dưới dạng JSON
        ResponseEntity<?> orderResponseEntity =
                restTemplate.exchange("http://192.168.4.167:8080/api/received-model",
                HttpMethod.POST,
                entity,
                ExportOrderConfirmModel.class);

        if (orderResponseEntity.getStatusCode() == HttpStatus.OK) {
            ExportOrder exportOrder = exportOrderService.findById(orderModel.getOrderId());
//            exportOrder.setDeliveryDate(orderModel.getDeliveryDate());
            exportOrder.setTotal(orderModel.getTotalPrice());
            exportOrder.setStatus("In delivery process.");

            exportOrderService.save(exportOrder);

            return orderResponseEntity;
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // (3) Nhận status complete từ anh Tùng, hoàn tất đơn hàng
    @PostMapping("/complete")
    public ResponseEntity<?> getCompleteResponse(@RequestBody ExportOrderConfirmDto exportOrderConfirmDto) {
        ExportOrder exportOrder = exportOrderService.findById(exportOrderConfirmDto.getId());
        if (exportOrder != null) {
            exportOrder.setStatus("Done!");
            exportOrderService.save(exportOrder);
            return new ResponseEntity<>("Tks anh Tung", HttpStatus.OK);
        }
        return null;
    }


}


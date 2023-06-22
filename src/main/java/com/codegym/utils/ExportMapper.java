package com.codegym.utils;

import com.codegym.dto.exportDto.request.ExportOrderDetailRequestDto;
import com.codegym.dto.exportDto.request.ExportOrderRequestDto;
import com.codegym.entity.Product;
import com.codegym.entity.exportEntity.ExportOrder;
import com.codegym.entity.exportEntity.ExportOrderDetails;
import com.codegym.repository.exportRepository.ExportOrderDetailsRepository;
import com.codegym.repository.exportRepository.ExportOrderRepository;
import com.codegym.service.exportService.ExportProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ExportMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ExportOrderRepository exportOrderRepository;

    @Autowired
    private ExportProductService exportProductService;

    @Autowired
    private ExportOrderDetailsRepository exportOrderDetailsRepository;

    public ExportOrder convertDtoToEntity(ExportOrderRequestDto dto) {
        ExportOrder exportOrderMapper = new ExportOrder();

        List<ExportOrderDetails> exportOrderDetailsList = new ArrayList<>();
        List<ExportOrderDetailRequestDto> exportOrderDetailRequestDtos = dto.getProductItemListDto();
//        mapper.map(dto, exportOrderMapper);
        return exportOrderMapper;
//        ExportOrder exportOrder = new ExportOrder();
//        List<ExportOrderDetails> exportOrderDetailsList = convertDtoListToEntityList(dto.getRequestDetailDtoList());
//
//        exportOrder.setId(dto.getOrderId());
//        return exportOrder;
    }

    private List<ExportOrderDetails> convertDtoListToEntityList(List<ExportOrderDetailRequestDto> exportOrderDetailRequestDtos) {

        List<ExportOrderDetails> exportOrderDetailsListMapper = new ArrayList<>();
        exportOrderDetailRequestDtos.stream().map(exportOrderDetailRequestDto -> exportOrderDetailsListMapper);
        return exportOrderDetailsListMapper;

//        List<ExportOrderDetails> exportOrderDetailsList = new ArrayList<>();
//        for (ExportOrderDetailRequestDto dto : exportOrderDetailRequestDtos) {
//            ExportOrderDetails entity = new ExportOrderDetails();
//
//        }
//        return exportOrderDetailsList;

    }

    public List<ExportOrderDetails> convertListDetailsDtoToListDetailEntity(List<ExportOrderDetailRequestDto> detailRequestDtoList) {
        List<ExportOrderDetails> exportOrderDetailsList = new ArrayList<>();

        for (ExportOrderDetailRequestDto dto : detailRequestDtoList) {
            Optional<Product> product = exportProductService.findProductById(Long.parseLong(dto.getProductName())); // Keu anh tung doi ten
            Optional<ExportOrder> order = exportOrderRepository.findById(dto.getId());

            if (product.isPresent() && order.isPresent()) {
                ExportOrderDetails orderDetails = new ExportOrderDetails();
                orderDetails.setOrder(order.get());
                orderDetails.setProduct(product.get());
                orderDetails.setQuantity(dto.getQuantity());
                exportOrderDetailsList.add(orderDetails);
                exportOrderDetailsRepository.save(orderDetails);
            }

        }

        return detailRequestDtoList.stream()
                .map( exportOrderDetailDto -> modelMapper.map( detailRequestDtoList , ExportOrderDetails.class ) )
                .collect( Collectors.toList());
    }
}

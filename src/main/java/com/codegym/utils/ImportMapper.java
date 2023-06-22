package com.codegym.utils;

import com.codegym.dto.ProductDto;
import com.codegym.dto.importDto.request.ImportOrderDetailRequestDto;
import com.codegym.dto.importDto.request.ImportOrderRequestDto;
import com.codegym.dto.importDto.response.OrderConfirmResponseDto;
import com.codegym.dto.importDto.response.TruckDto;
import com.codegym.entity.importEntity.ImportOrder;
import com.codegym.entity.importEntity.ImportOrderDetails;
import com.codegym.entity.importEntity.Truck;
import com.codegym.model.ProductModel;
import com.codegym.model.importModel.request.ImportOrderDetailModel;
import com.codegym.model.importModel.request.ImportOrderModel;
import com.codegym.repository.importRepository.ImportOrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ImportMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ImportOrderRepository importOrderRepository;

    // OrderDto => OrderModel
    public ImportOrderModel convertDtoToModel(ImportOrderRequestDto orderDto) {
        ImportOrderModel orderModelMapper = new ImportOrderModel();

//        List<ImportOrderDetailRequestDto> importOrderDetailRequestDtos = orderDto.getDetailDtoList();
//        List<ImportOrderDetailModel> importOrderDetailModels = convertDtoListToModelList(importOrderDetailRequestDtos);

//        orderModelMapper.setDetailModelList(importOrderDetailModels);
//
//        modelMapper.map(orderDto, orderModelMapper);
//        ImportOrderModel orderModelMapper = modelMapper.map(orderDto, ImportOrderModel.class);
        return orderModelMapper;

    }

    // List OrderDetailsDto => List OrderDetailsModel
    private List<ImportOrderDetailModel> convertDtoListToModelList(List<ImportOrderDetailRequestDto> importOrderDetailRequestDtos) {

        List<ImportOrderDetailModel> orderDetailModelList = new ArrayList<>();

        return importOrderDetailRequestDtos.parallelStream()
                .map( orderDetailRequestDto -> modelMapper.map( orderDetailRequestDto , ImportOrderDetailModel.class ) )
                .collect( Collectors.toList());

//        for (ImportOrderDetailRequestDto detailDto : importOrderDetailRequestDtos) {
//            ImportOrderDetailModel orderDetailModel = new ImportOrderDetailModel();
//            orderDetailModel.setOrder_id(detailDto.getOrder_id());
//
//            ProductDto productDto = detailDto.getProductDto();
//            ProductModel productModel = convertDtoToModelProduct(productDto);
//            orderDetailModel.setProductModel(productModel);
//
//            orderDetailModel.setQuantity(detailDto.getQuantity());
//            orderDetailModelList.add(orderDetailModel);
//        }
    }

    // productDto => productModel
    private ProductModel convertDtoToModelProduct(ProductDto productDto) {
        ProductModel productModelMapper = new ProductModel();
        modelMapper.map(productDto, productModelMapper);
        return productModelMapper;
    }

    // OrderModel => Entity
    public ImportOrder convertModelToEntity(ImportOrderModel model) {
        ImportOrder importOrderMapper = new ImportOrder();
//        List<ImportOrderDetailModel> detailModelList = model.getDetailModelList();
//        List<ImportOrderDetails> importOrderDetailsList = convertOrderModelListToOrderEntityList(detailModelList);
//        importOrder.setOrderDetailsList(importOrderDetailsList);
        modelMapper.map(model, importOrderMapper);
        return importOrderMapper;
    }

    // Chưa hoàn thành
    //List OrderDetailModel => List OrderDetailEntity
    private List<ImportOrderDetails> convertOrderModelListToOrderEntityList(List<ImportOrderDetailModel> importOrderDetailModels) {
        List<ImportOrderDetails> orderDetailsListMapper = new ArrayList<>();

        //?
        importOrderDetailModels.stream().map(importOrderDetailModel -> orderDetailsListMapper);

//        for (ImportOrderDetailModel ele : importOrderDetailModels) {
//            ImportOrderDetails entity = new ImportOrderDetails();
//            entity.setQuantity(ele.getQuantity());
//        }

        return orderDetailsListMapper;
    }

    // OrderDto => Order entity
    public ImportOrder convertFromDtoToEntity(OrderConfirmResponseDto responseDto) {
        Optional<ImportOrder> entity = importOrderRepository.findById(responseDto.getId());

        if (entity.isPresent()) {
            modelMapper.map(responseDto, entity.get());
//            entity.get().setTotalPrice(responseDto.getTotalPrice());
//            entity.get().setDeliveryDate(responseDto.getDelivery());
//            entity.get().setStatus("Delivering");
//            return entity.get();
        }
        return entity.get();
    }

    public List<Truck> convertListTruckDtoToListTruckEntity(List<TruckDto> truckDtoList) {

        return truckDtoList.stream()
                .map( truckDto -> modelMapper.map( truckDto , Truck.class ) )
                .collect( Collectors.toList());
    }
}

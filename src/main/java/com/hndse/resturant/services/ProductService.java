package com.hndse.resturant.services;


import com.hndse.resturant.dtos.request.ProductRequestDto;
import com.hndse.resturant.dtos.response.ProductResponseDto;
import com.hndse.resturant.dtos.response.ResponseDto;
import com.hndse.resturant.entities.Product;
import com.hndse.resturant.mappers.ProductMapper;
import com.hndse.resturant.repos.ProductRepository;
import com.hndse.resturant.utilities.FileUpload;
import com.hndse.resturant.utilities.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ResponseDto responseDto;

    @Value("${D:\\NIBM Projects\\EAD 2.0\\CW\\resturant\\src\\main\\resources\\static\\Images}")
    private String fileUploadDir;

    @Transactional
    public void addProduct(ProductRequestDto productRequestDto) {
        try {
            if (productRequestDto.getName() == null || productRequestDto.getName().trim().isEmpty() || productRequestDto.getDescription() == null || productRequestDto.getDescription().trim().isEmpty()) {
                responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDto.setMessage("Product name or description is empty");
                responseDto.setContent(ProductMapper.mapToProductResponseDto(productRequestDto));
            } else if (productRepository.existsByName(productRequestDto.getName())) {
                responseDto.setCode(VarList.RSP_DUPLICATED);
                responseDto.setMessage("Product name already exists");
                responseDto.setContent(ProductMapper.mapToProductResponseDto(productRequestDto));
            } else if (productRequestDto.getPrice() == null || productRequestDto.getPrice() < 0) {
                responseDto.setCode(VarList.RSP_TOKEN_INVALID);
                responseDto.setMessage("Product price is invalid");
                responseDto.setContent(ProductMapper.mapToProductResponseDto(productRequestDto));
            } else if (productRequestDto.getCategory() == null || productRequestDto.getCategory().trim().isEmpty()) {
                responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDto.setMessage("Product category is empty");
                responseDto.setContent(ProductMapper.mapToProductResponseDto(productRequestDto));
            } else {
                MultipartFile File = productRequestDto.getImage();
                if (File != null && !File.isEmpty()) {
                    FileUpload.validateFile(File, 5 * 1024 * 1024, "image/jpeg", "image/png", "image/gif");
                    String fileName = FileUpload.saveFile(fileUploadDir, File);
                    productRequestDto.setImagePath(fileName);
                    productRepository.save(ProductMapper.mapToProduct(productRequestDto));
                    responseDto.setCode(VarList.RSP_SUCCESS);
                    responseDto.setMessage("Product added successfully");
                    responseDto.setContent(ProductMapper.mapToProductResponseDto(productRequestDto));


                } else {
                    responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
                    responseDto.setMessage("Product image is empty");
                    responseDto.setContent(ProductMapper.mapToProductResponseDto(productRequestDto));
                }

            }

        } catch (Exception e) {
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(e.getMessage());
            responseDto.setContent(ProductMapper.mapToProductResponseDto(productRequestDto));
        }

    }

    @Transactional
    public void updateProduct(ProductRequestDto productRequestDto) {
        try {
            if(productRequestDto.getId() == null){
                responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDto.setMessage("Product id is empty");
                responseDto.setContent(ProductMapper.mapToProductResponseDto(productRequestDto));
            }
            else if (productRequestDto.getName() == null || productRequestDto.getName().trim().isEmpty() || productRequestDto.getDescription() == null || productRequestDto.getDescription().trim().isEmpty()) {
                responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDto.setMessage("Product name or description is empty");
                responseDto.setContent(ProductMapper.mapToProductResponseDto(productRequestDto));
            } else if (productRequestDto.getPrice() == null || productRequestDto.getPrice() < 0) {
                responseDto.setCode(VarList.RSP_TOKEN_INVALID);
                responseDto.setMessage("Product price is invalid");
                responseDto.setContent(ProductMapper.mapToProductResponseDto(productRequestDto));
            } else if (productRequestDto.getCategory() == null || productRequestDto.getCategory().trim().isEmpty()) {
                responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDto.setMessage("Product category is empty");
                responseDto.setContent(ProductMapper.mapToProductResponseDto(productRequestDto));
            } else {
                if (productRepository.existsById(productRequestDto.getId())) {

                        Product product = productRepository.findById(productRequestDto.getId()).orElseThrow(() -> new RuntimeException("Product not found"));

                        //save new image
                        MultipartFile File = productRequestDto.getImage();
                        if (File != null && !File.isEmpty()) {
                            if (product.getImagePath() != null) {
                                FileUpload.deleteFile(fileUploadDir, product.getImagePath());
                            }
                            FileUpload.validateFile(File, 5 * 1024 * 1024, "image/jpeg", "image/png", "image/gif");
                            String fileName = FileUpload.saveFile(fileUploadDir, File);
                            productRequestDto.setImagePath(fileName);
                            productRepository.save(ProductMapper.mapToProduct(productRequestDto));
                            responseDto.setCode(VarList.RSP_SUCCESS);
                            responseDto.setMessage("Product Edited successfully");
                            responseDto.setContent(ProductMapper.mapToProductResponseDto(productRequestDto));


                        } else {
                            productRequestDto.setImagePath(product.getImagePath());
                            productRepository.save(ProductMapper.mapToProduct(productRequestDto));
                            responseDto.setCode(VarList.RSP_SUCCESS);
                            responseDto.setMessage("Product Edited successfully");
                            responseDto.setContent(ProductMapper.mapToProductResponseDto(productRequestDto));
                        }

                }else {
                    responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
                    responseDto.setMessage("Product Not found");
                    responseDto.setContent(ProductMapper.mapToProductResponseDto(productRequestDto));
                }


            }

        } catch (Exception e) {
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(e.getMessage());
            responseDto.setContent(ProductMapper.mapToProductResponseDto(productRequestDto));
        }
    }

    @Transactional(readOnly = true)
    public void getallProducts() {
        try{
            List<Product> products = productRepository.findAll();
            List<ProductResponseDto> productResponseDto = new ArrayList<>();
            if(!products.isEmpty()){
              for(Product product : products){
                  productResponseDto.add(ProductMapper.mapToProductResponseDto(product));
              }
              responseDto.setCode(VarList.RSP_SUCCESS);
              responseDto.setMessage("All products successfully");
              responseDto.setContent(productResponseDto);

            }else{
                responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDto.setMessage("No products found");
                responseDto.setContent("No products found");
            }
        }catch(Exception e){
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(e.getMessage());
            responseDto.setContent(null);
        }
    }

    @Transactional
    public void deleteProduct(ProductRequestDto productRequestDto) {
        try{
            if(productRequestDto.getId() == null){
                responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDto.setMessage("Product id is empty");
                responseDto.setContent(ProductMapper.mapToProductResponseDto(productRequestDto));
            }else if(productRepository.existsById(productRequestDto.getId())) {
                productRepository.deleteById(productRequestDto.getId());
                responseDto.setCode(VarList.RSP_SUCCESS);
                responseDto.setMessage("Product deleted successfully");
                responseDto.setContent(ProductMapper.mapToProductResponseDto(productRequestDto));
            }else {
                responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDto.setMessage("Product not found");
                responseDto.setContent(ProductMapper.mapToProductResponseDto(productRequestDto));
            }
        }catch(Exception e){
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(e.getMessage());
            responseDto.setContent(null);
        }
    }

}

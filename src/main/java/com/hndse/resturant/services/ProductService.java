package com.hndse.resturant.services;


import com.hndse.resturant.dtos.request.ProductRequestDto;
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
            if (productRequestDto.getName() == null || productRequestDto.getName().trim().equals("") || productRequestDto.getDescription() == null || productRequestDto.getDescription().trim().equals("")) {
                responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDto.setMessage("Product name or description is empty");
                responseDto.setContent(ProductMapper.mapToProductResponseDto(productRequestDto));
            }
            else if(productRepository.existsByName(productRequestDto.getName())) {
                responseDto.setCode(VarList.RSP_DUPLICATED);
                responseDto.setMessage("Product name already exists");
                responseDto.setContent(ProductMapper.mapToProductResponseDto(productRequestDto));
            }
            else if (productRequestDto.getPrice() == null || productRequestDto.getPrice() < 0) {
                responseDto.setCode(VarList.RSP_TOKEN_INVALID);
                responseDto.setMessage("Product price is invalid");
                responseDto.setContent(ProductMapper.mapToProductResponseDto(productRequestDto));
            } else if (productRequestDto.getCategory() == null || productRequestDto.getCategory().trim().equals("")) {
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
}

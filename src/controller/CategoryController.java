package com.Excel.controll;

import com.Excel.model.Category;
import com.Excel.repository.CategoryRepository;
import com.Excel.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
public class CategoryController {

    @Autowired
    private ExcelService service;



    @Autowired
    private CategoryRepository repo;

    @PostMapping("/Save-data")
    public String saveData(@RequestBody Category category){
        repo.save(category);
        return "Save Data Successfully......";
    }


    @GetMapping("/download/categories")
    public ResponseEntity<InputStreamResource> download() {
        try {
            String filename = "categories.xlsx";
            ByteArrayInputStream actualData = service.getActualData();
            InputStreamResource file = new InputStreamResource(actualData);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(file);

        } catch (IOException e) {
            // Handle the exception gracefully, e.g., log the error or return a custom response
            return ResponseEntity.internalServerError().build();
        }
    }
}

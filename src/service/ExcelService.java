package com.Excel.service;

import com.Excel.helper.Helper;
import com.Excel.model.Category;
import com.Excel.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelService {

    @Autowired
    private CategoryRepository repo;


    public ByteArrayInputStream getActualData() throws IOException {
        List<Category> all=repo.findAll();
        ByteArrayInputStream byteArrayInputStream= Helper.dataToExcel(all);
        return byteArrayInputStream;
    }

}

package com.manage.hslibrary.DAO;
import com.manage.hslibrary.DTO.*;

import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Component;

import javax.sql.*;
import java.util.*;
@Component
public class BookDAO {
    private BookDTO bookDTO;
    private JdbcTemplate jdbcTemplate;

}

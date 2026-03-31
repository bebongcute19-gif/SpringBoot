package re.session03.service;

import re.session03.model.Instructor;

import java.util.List;

public interface InstructorService {

    // Lấy tất cả
    List<Instructor> getAll();

    // Lấy theo id
    Instructor getById(String id);

    // Tạo mới
    Instructor create(Instructor instructor);

    // Cập nhật
    Instructor update(String id, Instructor instructor);

    // Xóa
    Instructor deleteById(String id);
}
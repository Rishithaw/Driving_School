package com.ijse.gdse.project.model;

import com.ijse.gdse.project.dto.BookingDTO;
import com.ijse.gdse.project.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookingModel {
    public String getNextBookingId() throws SQLException {
        ResultSet rst = CrudUtil.execute("select booking_id from booking order by booking_id desc limit 1");
        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIndex = i + 1;
            return String.format("B%03d", newIndex);
        }
        return "B001";
    }

    public boolean saveBooking(BookingDTO bookingDTO) throws SQLException {
        return CrudUtil.execute("insert into booking values(?,?,?,?)",
                bookingDTO.getBookingId(),
                bookingDTO.getRescheduleReason(),
                bookingDTO.getBookingDate(),
                bookingDTO.getInstructorId()
        );
    }

    public ArrayList<BookingDTO> getAllBooking() throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from booking");
        ArrayList<BookingDTO> bookingDTOS = new ArrayList<>();
        while (rst.next()) {
            BookingDTO bookingDTO = new BookingDTO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getDate(3),
                    rst.getString(4)
            );
            bookingDTOS.add(bookingDTO);
        }
        return bookingDTOS;
    }

    public boolean updateBooking(BookingDTO bookingDTO) throws SQLException {
        return CrudUtil.execute(
                "update booking set reschedule_reason=?, booking_date=?, in_id=? where booking_id=?",
                bookingDTO.getRescheduleReason(),
                bookingDTO.getBookingDate(),
                bookingDTO.getInstructorId(),
                bookingDTO.getBookingId()
        );
    }

    public boolean deleteBooking(String bookingId) throws SQLException {
        return CrudUtil.execute("delete from booking where booking_id=?", bookingId);
    }

    public ArrayList<String> getAllBookingId() throws SQLException {
        ResultSet rst = CrudUtil.execute("select booking_id from booking");
        ArrayList<String> bookingIds = new ArrayList<>();
        while (rst.next()) {
            bookingIds.add(rst.getString(1));
        }
        return bookingIds;
    }

    public BookingDTO findById(String selectedBookingId) throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from booking where booking_id=?", selectedBookingId);

        if (rst.next()) {
            return new BookingDTO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getDate(3),
                    rst.getString(4)
            );
        }
        return null;
    }
}

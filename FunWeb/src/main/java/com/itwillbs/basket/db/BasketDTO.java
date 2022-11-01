package com.itwillbs.basket.db;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//@Data : DTO 객체 생성에 필요한 모든 동작을 구현
//@Setter : set() 자동생성
//@Getter : get() 자동생성
//@ToString : toString() 자동생성
//@AllArgsConstructor : 오버로딩 생성자 자동생성

@Data
public class BasketDTO {
	private int b_num;
	private String b_m_id;
	private int b_g_num;
	private int b_g_amount;
	private String b_g_size;
	private String b_g_color;
	private Timestamp b_date;
}

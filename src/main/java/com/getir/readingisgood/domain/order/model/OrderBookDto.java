package com.getir.readingisgood.domain.order.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderBookDto {
	@NotNull
	private Long bookId;
	@Size(min = 1)
	private Integer amount;
}

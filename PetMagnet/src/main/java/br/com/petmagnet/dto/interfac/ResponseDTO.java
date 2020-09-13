package br.com.petmagnet.dto.interfac;

import java.util.List;

public interface ResponseDTO<E> {
	public List<E> toList();
}

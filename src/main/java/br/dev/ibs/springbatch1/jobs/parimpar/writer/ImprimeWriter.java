package br.dev.ibs.springbatch1.jobs.parimpar.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ImprimeWriter implements ItemWriter<String> {
	
	@Override
	public void write(final List<? extends String> list) {
		list.forEach(System.out::println);
	}
}

package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Choice;
import repositories.ChoiceRepository;

@Component
@Transactional
public class StringToChoiceConverter implements Converter<String, Choice>{

	@Autowired
	ChoiceRepository pollerRepository;


	@Override
	public Choice convert(final String text) {
		Choice res;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				res = null;
			else {
				id = Integer.valueOf(text);
				res = this.pollerRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return res;
	}
}

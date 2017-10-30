package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Choice;

@Component
@Transactional
public class ChoiceToStringConverter implements Converter<Choice, String>{

	@Override
	public String convert(final Choice poll) {
		String result;

		if (poll == null)
			result = null;
		else
			result = String.valueOf(poll.getId());

		return result;
	}
}

package com.forhadmethun.accountservice.utility.validation;

/**
 * @author Md Forhad Hossain
 * @since 02/10/20
 */

import java.util.function.Predicate;

public class ValidationImpl<K> implements Validation<K> {

	private Predicate<K> predicate;
	private String onErrorMessage;

	public static <K> ValidationImpl<K> from(Predicate<K> predicate, String onErrorMessage) {
		 return new ValidationImpl<K>(predicate, onErrorMessage);
	}

	private ValidationImpl(Predicate<K> predicate, String onErrorMessage) {
		this.predicate = predicate;
		this.onErrorMessage = onErrorMessage;
	}

	@Override
	public ValidationResult test(K param) {
		return predicate.test(param) ? ValidationResult.ok() : ValidationResult.fail(onErrorMessage);
	}

}

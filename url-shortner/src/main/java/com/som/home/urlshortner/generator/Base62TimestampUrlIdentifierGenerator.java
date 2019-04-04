package com.som.home.urlshortner.generator;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Component;

import com.som.home.urlshortner.util.Base62;

@Component
public class Base62TimestampUrlIdentifierGenerator implements UrlIdentifierGenerator {

	protected AtomicInteger counter = new AtomicInteger();
	
	/* (non-Javadoc)
	 * @see com.som.home.urlshortner.generator.UrlIdentifierGenerator#generate()
	 */
	@Override
	public String generate() {
        final int counterValue = counter.getAndUpdate((operand) -> (operand + 1) % 1000);
        final long base10Id = Long.valueOf("" + counterValue + System.currentTimeMillis());
        return Base62.fromBase10(base10Id);
    }
}

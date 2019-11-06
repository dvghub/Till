package nu.educom.calculateChange;

/**
 * Simple implementation of a money class that wraps a BigDecimal 
 */
public class Money {

	private final long cents;
	
	public static final Money ZERO = new Money(0);

	public Money(int val) {
		cents = val * 100;
	}

	public Money(double val) {
		cents = (long)(val * 100.0);
	}

	private Money(long cents) {
		this.cents = cents;
	}

	@Override
	public String toString() {
		return toString('E');
	}

	private String toString(Character currencySymbol) {
		if (currencySymbol == null) {
			return String.format("%1$,.2f", cents/100.0);
		} 
		return String.format("%1s %2$,.2f", currencySymbol, cents/100.0);
	}

	/**
	 * Compare to another money object
	 * @param val the other value
	 * @return -1, 0, or 1 as this Money is numerically less than, equal to, or greater than val. 
	 */
	public int compareTo(Money val) {
		return Long.compare(cents, val.cents);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Money)) {
			return false;
		}
		return equals((Money) obj);
	}

	private boolean equals(Money other) {
		if (other == null) { 
			return false; 
		}
		return cents == other.cents;		
	}

	public Money add(Money val) {
		return new Money(cents + val.cents);
	}

	public Money subtract(Money val) {
		return new Money(cents - val.cents);
	}

	public Money times(double count) {
		return new Money((long)(cents * count));
	}
	
	/**
	 * Divide by a number
	 * @param divisor the number to divide by
	 * @return  the value / divisor
	 */
	public Money divide(double divisor) {
		return new Money((long)(cents / divisor));
	}

	/**
	 * Find the ratio between two amounts 
	 * @param divisor the amount to divide by
	 * @return the resulting ratio
	 */
	public double divide(Money divisor) {
		return (double)cents / (double)divisor.cents;
	}

	/**
	 * Divide by a whole number
	 * @param divisor the number to divide by
	 * @return  the value / divisor
	 */
	public long divideToIntegralValue(Money divisor) {
		return cents / divisor.cents;
	}

	
	/**
	 * Give the remainder after dividing by a number
	 * @param divisor the number to divide by
	 * @return the remainder after value / divisor
	 */
	public Money remainder(double divisor) {
		return new Money((long)(cents % divisor));
	}
	
	/**
	 * Give the remainder after dividing by a number
	 * @param divisor the number to divide by
	 * @return the remainder after value / divisor
	 */
	public double remainder(Money divisor) {
		return (double)cents % (double)divisor.cents;
	}
}

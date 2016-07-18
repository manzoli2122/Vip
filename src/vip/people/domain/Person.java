package vip.people.domain;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;


@MappedSuperclass
public class Person extends PersistentObjectSupport implements Comparable<Person> {
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** The person's name. */
	@Basic
	@NotNull
	@Size(max = 100)
	protected String name;

	/** The person's birth date. */
	@Temporal(TemporalType.DATE)
	protected Date birthDate;

	/** The person's gender: 'M' (male) or 'F' (female). */
	@Basic
	protected Gender gender;

	/** Getter for name. */
	public String getName() {
		return name;
	}

	/** Setter for name. */
	public void setName(String name) {
		this.name = name;
	}

	/** Getter for birthDate. */
	public Date getBirthDate() {
		return birthDate;
	}

	/** Setter for birthDate. */
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	/** alterei aqui para ser um enum (manzoli) */
	/** Getter for gender. */
	public Gender getGender() {
		return gender;
	}

	/** alterei aqui para ser um enum (manzoli) */
	/** Setter for gender. */
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	/** @see java.lang.Comparable#compareTo(java.lang.Object) */
	@Override
	public int compareTo(Person o) {
		// Compare the persons' names
		if (name == null)
			return 1;
		if (o.name == null)
			return -1;
		int cmp = name.compareTo(o.name);
		if (cmp != 0)
			return cmp;

		// If it's the same name, check if it's the same entity.
		return uuid.compareTo(o.uuid);
	}

	/** @see br.ufes.inf.nemo.util.ejb3.persistence.PersistentObjectSupport#toString() */
	@Override
	public String toString() {
		return name;
	}
}

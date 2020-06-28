package org.eclipse.passage.lic.internal.api.conditions;

import java.util.Objects;

public abstract class UserRole {

	private final String role;

	protected UserRole(String role) {
		Objects.requireNonNull(role, "UserRole::role"); //$NON-NLS-1$
		this.role = role;
	}

	@Override
	public boolean equals(Object obj) {
		if (!ConditionAction.class.isInstance(obj)) {
			return false;
		}
		return role.equals(((ConditionAction) obj).name());
	}

	@Override
	public int hashCode() {
		return role.hashCode();
	}

	public final String name() {
		return role;
	}

	@Override
	public String toString() {
		return role;
	}

	public static final class Admin extends UserRole {

		public Admin() {
			super("admin"); //$NON-NLS-1$
		}

	}

	public static final class Licensee extends UserRole {

		public Licensee() {
			super("licensee"); //$NON-NLS-1$
		}

	}

	public static final class Operator extends UserRole {

		public Operator() {
			super("operator"); //$NON-NLS-1$
		}

	}

	public static final class Of extends UserRole {

		public Of(String role) {
			super(role);
		}

	}

}

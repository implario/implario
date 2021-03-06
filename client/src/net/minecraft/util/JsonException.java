package net.minecraft.util;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonException extends IOException {

	private final List<JsonException.Entry> list = new ArrayList<>();
	private final String message;

	public JsonException(String message) {
		this.list.add(new JsonException.Entry());
		this.message = message;
	}

	public JsonException(String p_i45280_1_, Throwable p_i45280_2_) {
		super(p_i45280_2_);
		this.list.add(new JsonException.Entry());
		this.message = p_i45280_1_;
	}

	public void func_151380_a(String p_151380_1_) {
		this.list.get(0).func_151373_a(p_151380_1_);
	}

	public void func_151381_b(String p_151381_1_) {
		this.list.get(0).field_151376_a = p_151381_1_;
		this.list.add(0, new JsonException.Entry());
	}

	public String getMessage() {
		return "Invalid " + this.list.get(this.list.size() - 1).toString() + ": " + this.message;
	}

	public static JsonException func_151379_a(Exception p_151379_0_) {
		if (p_151379_0_ instanceof JsonException) {
			return (JsonException) p_151379_0_;
		}
		String s = p_151379_0_.getMessage();

		if (p_151379_0_ instanceof FileNotFoundException) {
			s = "File not found";
		}

		return new JsonException(s, p_151379_0_);
	}

	public static class Entry {

		private String field_151376_a;
		private final List<String> field_151375_b;

		private Entry() {
			this.field_151376_a = null;
			this.field_151375_b = new ArrayList<>();
		}

		private void func_151373_a(String p_151373_1_) {
			this.field_151375_b.add(0, p_151373_1_);
		}

		public String func_151372_b() {
			return StringUtils.join(this.field_151375_b, "->");
		}

		public String toString() {
			return this.field_151376_a != null ? !this.field_151375_b.isEmpty() ? this.field_151376_a + " " + this.func_151372_b() : this.field_151376_a : !this.field_151375_b.isEmpty() ? "(Unknown file) " + this.func_151372_b() : "(Unknown file)";
		}

	}

}

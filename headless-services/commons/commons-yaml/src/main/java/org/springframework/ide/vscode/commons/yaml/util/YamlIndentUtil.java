/*******************************************************************************
 * Copyright (c) 2015 Pivotal, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Pivotal, Inc. - initial API and implementation
 *******************************************************************************/
package org.springframework.ide.vscode.commons.yaml.util;

import org.springframework.ide.vscode.commons.util.Assert;
import org.springframework.ide.vscode.commons.yaml.structure.YamlDocument;

import com.google.common.base.Strings;

/**
 * Helper methods to manipulate indentation levels in yaml content.
 *
 * @author Kris De Volder
 */
public class YamlIndentUtil {

	/**
	 * Number of indentation levels (spaces) added between a child and parent.
	 * TODO: replace this constant by (existing!) yedit preference value
	 */
	public static final int INDENT_BY = 2;

	public static final String INDENT_STR = Strings.repeat(" ", INDENT_BY);

	/**
	 * Some functions introduce line separators and this may depend on the context (i.e. default line separator
	 * for the current document).
	 */
	public final String NEWLINE;

	public YamlIndentUtil(String newline) {
		this.NEWLINE = newline;
		Assert.isNotNull(NEWLINE);
	}

	public YamlIndentUtil(YamlDocument doc) {
		this(doc.getDocument().getDefaultLineDelimiter());
	}

	/**
	 * Determine the 'known minimum' of two indentation levels. Correctly handle
	 * when either one or both indent levels are '-1' (unknown).
	 */
	public static int minIndent(int a, int b) {
		if (a==-1) {
			return b;
		} else if (b==-1) {
			return a;
		} else {
			return Math.min(a, b);
		}
	}

	public static void addIndent(int indent, StringBuilder buf) {
		for (int i = 0; i < indent; i++) {
			buf.append(' ');
		}
	}

	public void addNewlineWithIndent(int indent, StringBuilder buf) {
		buf.append(NEWLINE);
		addIndent(indent, buf);
	}

	public String newlineWithIndent(int indent) {
		StringBuilder buf = new StringBuilder();
		addNewlineWithIndent(indent, buf);
		return buf.toString();
	}

	/**
	 * Applies a certain level of indentation to all new lines in the given text. Newlines
	 * are expressed by '\n' characters in the text will be replaced by the appropriate
	 * newline + indent.
	 * <p>
	 * Notes:
	 *  - '\n' are replaced by the default line delimeter for the current document.
	 *  - indentation is not applied to the first line of text.
	 */
	public String applyIndentation(String text, int indentBy) {
		return text.replaceAll("\\n", newlineWithIndent(indentBy));
	}

	/**
	 * Increase offset by indentation. Take care when 'indent' is -1 (unkownn) to
	 * just return offset unmodified.
	 */
	public static int addToOffset(int offset, int indent) {
		if (indent==-1) {
			return offset;
		}
		return offset + indent;
	}

	/**
	 * Add given offset to an indent, correctly handling the case
	 * were the indent is -1 (unknown)
	 */
	public static int add(int indent, int adjustment) {
		if (indent==-1) {
			return indent; //indent remains unknown
		}
		indent += adjustment;
		return indent>=0 ? indent : 0;
	}

}

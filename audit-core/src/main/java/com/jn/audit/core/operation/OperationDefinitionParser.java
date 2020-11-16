package com.jn.audit.core.operation;

import com.jn.audit.core.model.OperationDefinition;
import com.jn.langx.Parser;

/**
 * OperationDefinition  解析器，解析出OperationDefinition
 *
 * @param <E>
 */
public interface OperationDefinitionParser<E> extends Parser<E, OperationDefinition> {
    String getName();
}

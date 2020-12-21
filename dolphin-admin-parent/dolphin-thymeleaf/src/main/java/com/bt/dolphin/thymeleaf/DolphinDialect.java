package com.bt.dolphin.thymeleaf;

import java.util.LinkedHashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.dialect.IExpressionObjectDialect;
import org.thymeleaf.expression.IExpressionObjectFactory;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

/**
 * @author 小懒虫
 * @date 2018/8/14
 */
public class DolphinDialect extends AbstractProcessorDialect implements IExpressionObjectDialect {

    private static final String NAME = "dolphinDialect";
    private static final String PREFIX = "mo";
    private IExpressionObjectFactory expressionObjectFactory = null;

    public DolphinDialect() {
        super(NAME, PREFIX, StandardDialect.PROCESSOR_PRECEDENCE);
    }

    @Override
    public Set<IProcessor> getProcessors(String dialectPrefix) {
        Set<IProcessor> processors = new LinkedHashSet<IProcessor>();
     /*   processors.add(new SelectDictAttrProcessor(TemplateMode.HTML, dialectPrefix));
        processors.add(new SelectListAttrProcessor(TemplateMode.HTML, dialectPrefix));*/
        return processors;
    }

    @Override
    public IExpressionObjectFactory getExpressionObjectFactory() {
        if (this.expressionObjectFactory == null) {
            this.expressionObjectFactory = new DophinExpressionObjectFactory();
        }
        return this.expressionObjectFactory;
    }
}

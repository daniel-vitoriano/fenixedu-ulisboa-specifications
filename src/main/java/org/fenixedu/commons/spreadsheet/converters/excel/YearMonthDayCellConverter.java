package org.fenixedu.commons.spreadsheet.converters.excel;

import org.fenixedu.commons.spreadsheet.converters.CellConverter;
import org.joda.time.YearMonthDay;

public class YearMonthDayCellConverter implements CellConverter {
    @Override
    public Object convert(Object source) {
        return (source != null) ? ((YearMonthDay) source).toString("yyyy-MM-dd") : null;
    }
}

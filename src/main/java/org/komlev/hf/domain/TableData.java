package org.komlev.hf.domain;

import java.util.List;

/**
 * Description.
 *
 * @author <a href="mailto:AlexKomlev@rambler.ru">Aleksey Komlev</a>
 * @version 31.08.2014
 */
public class TableData<T> {

    private int draw;
    private int recordsTotal;
    private int recordsFiltered;
    private List<T> data;

    public TableData(int draw, int recordsTotal, int recordsFiltered, List<T> data) {
        this.draw = draw;
        this.recordsTotal = recordsTotal;
        this.recordsFiltered = recordsFiltered;
        this.data = data;
    }

    public int getDraw() {
        return draw;
    }

    public int getRecordsTotal() {
        return recordsTotal;
    }

    public int getRecordsFiltered() {
        return recordsFiltered;
    }

    public List<T> getData() {
        return data;
    }
}

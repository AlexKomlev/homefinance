package org.komlev.hf.domain;

import org.hibernate.criterion.Criterion;

import java.util.Map;

/**
 * Created by Alex on 16.01.2016.
 */
public class HomeFilter {

    private String initialAlias;

    private Map<String, String> aliases;

    private Map<String, Integer> pageInfo;

    private Criterion filter;


    public String getInitialAlias() {
        return initialAlias;
    }

    public void setInitialAlias(String initialAlias) {
        this.initialAlias = initialAlias;
    }

    public Map<String, String> getAliases() {
        return aliases;
    }

    public void setAliases(Map<String, String> aliases) {
        this.aliases = aliases;
    }

    public Map<String, Integer> getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(Map<String, Integer> pageInfo) {
        this.pageInfo = pageInfo;
    }

    public Criterion getFilter() {
        return filter;
    }

    public void setFilter(Criterion filter) {
        this.filter = filter;
    }
}

package com.asesoftware.bancow.web.aplicacion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import com.asesoftware.bancow.modelo.utils.UtilConstantes;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class FilterService {

    private List<SelectItem> typesafeQuery;
    private Map<String, ArrayList<SelectItem>> conditionsList;
    private List<SelectItem> orderingList;

    @PostConstruct
    public void init() {

        this.conditionsList = this.getConditions();
        this.typesafeQuery = this.getTypesafeQuery();
        this.orderingList = this.getOrdering();
    }

    private ArrayList<SelectItem> getTypesafeQuery() {

        ArrayList<SelectItem> options = new ArrayList<SelectItem>();

        options.add(new SelectItem(UtilConstantes.TQ_CONDITIONS, "Conditions"));
        options.add(new SelectItem(UtilConstantes.TQ_ORDERING, "Ordering"));
        options.add(new SelectItem(UtilConstantes.TQ_GROUPING, "Grouping"));

        return options;

    }

    private Map<String, ArrayList<SelectItem>> getConditions() {

        Map<String, ArrayList<SelectItem>> filter = new HashMap<String, ArrayList<SelectItem>>();

        filter.put(UtilConstantes.EXPRESSION_METHODS, new ArrayList<SelectItem>());
        filter.put(UtilConstantes.CRITERIA_METHODS, new ArrayList<SelectItem>());
        filter.put(UtilConstantes.COMPOUND_METHODS, new ArrayList<SelectItem>());

        filter.get(UtilConstantes.EXPRESSION_METHODS).add(new SelectItem(UtilConstantes.EX_IS_NULL, "Is null"));
        filter.get(UtilConstantes.EXPRESSION_METHODS).add(new SelectItem(UtilConstantes.EX_IS_NOT_NULL, "Is not null"));
        filter.get(UtilConstantes.EXPRESSION_METHODS).add(new SelectItem(UtilConstantes.EX_IN, "In"));

        filter.get(UtilConstantes.CRITERIA_METHODS).add(new SelectItem(UtilConstantes.CR_EQUAL, "Equal"));
        filter.get(UtilConstantes.CRITERIA_METHODS).add(new SelectItem(UtilConstantes.CR_NOT_EQUAL, "Not equal"));
        filter.get(UtilConstantes.CRITERIA_METHODS).add(new SelectItem(UtilConstantes.CR_GREATER_THAN, "Greater than"));
        filter.get(UtilConstantes.CRITERIA_METHODS).add(new SelectItem(UtilConstantes.CR_GREATER_EQUAL, "Greater than or equal"));
        filter.get(UtilConstantes.CRITERIA_METHODS).add(new SelectItem(UtilConstantes.CR_GREATER_THAN, "Less than"));
        filter.get(UtilConstantes.CRITERIA_METHODS).add(new SelectItem(UtilConstantes.CR_GREATER_EQUAL, "Less than or equal"));
        filter.get(UtilConstantes.CRITERIA_METHODS).add(new SelectItem(UtilConstantes.CR_BETWEEN, "Between"));
        filter.get(UtilConstantes.CRITERIA_METHODS).add(new SelectItem(UtilConstantes.CR_LIKE, "Like"));

        filter.get(UtilConstantes.COMPOUND_METHODS).add(new SelectItem(UtilConstantes.CM_AND, "And"));
        filter.get(UtilConstantes.COMPOUND_METHODS).add(new SelectItem(UtilConstantes.CM_OR, "Or"));
        filter.get(UtilConstantes.COMPOUND_METHODS).add(new SelectItem(UtilConstantes.CM_NOT, "Not"));

        return filter;
    }

    private List<SelectItem> getOrdering() {

        List<SelectItem> options = new ArrayList<SelectItem>();
        options.add(new SelectItem(UtilConstantes.OR_ASC, "Ascending"));
        options.add(new SelectItem(UtilConstantes.OR_DESC, "Descending"));

        return options;
    }

    private List<String> getLabelList(List<SelectItem> ref) {
        List<String> output = new ArrayList<String>(ref.size());
        Iterator<SelectItem> it = ref.iterator();
        while (it.hasNext()) {
            output.add(it.next().getLabel());
        }
        return output;
    }

    public List<String> getTypesafeQueryStringList() {
        return this.getLabelList(this.typesafeQuery);
    }

    public List<SelectItem> getTypesafeQueryList() {
        return this.typesafeQuery;
    }

    public List<SelectItem> getConditionsList(String key) {
        return this.conditionsList.get(key);
    }

    public List<SelectItem> getOrderingList() {
        return orderingList;
    }

    public List<SelectItem> getMethods() {

        SelectItemGroup g1 = new SelectItemGroup("Expression");
        SelectItemGroup g2 = new SelectItemGroup("Criteria");

        List<SelectItem> ex = this.getConditionsList(UtilConstantes.EXPRESSION_METHODS);
        SelectItem[] exArr = new SelectItem[ex.size()];
        exArr = ex.toArray(exArr);

        List<SelectItem> cr = this.getConditionsList(UtilConstantes.CRITERIA_METHODS);
        SelectItem[] crArr = new SelectItem[cr.size()];
        crArr = cr.toArray(crArr);

        g1.setSelectItems(exArr);
        g2.setSelectItems(crArr);

        List<SelectItem> methods = new ArrayList<SelectItem>();
        methods.add(g2);
        methods.add(g1);

        return methods;
    }

}

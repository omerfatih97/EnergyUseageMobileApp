package com.example.codebasev1;

import android.content.Context;
import android.util.AttributeSet;

import androidx.core.content.ContextCompat;

import de.codecrafters.tableview.SortableTableView;
import de.codecrafters.tableview.model.TableColumnWeightModel;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;
import de.codecrafters.tableview.toolkit.SortStateViewProviders;
import de.codecrafters.tableview.toolkit.TableDataRowBackgroundProviders;
import com.example.codebasev1.data.Fatura;

public class SortableFaturaTableView extends SortableTableView<Fatura> {

    public SortableFaturaTableView(final Context context) {
        this(context, null);
    }

    public SortableFaturaTableView(final Context context, final AttributeSet attributes) {
        this(context, attributes, android.R.attr.listViewStyle);
    }

    public SortableFaturaTableView(final Context context, final AttributeSet attributes, final int styleAttributes) {
        super(context, attributes, styleAttributes);

        final SimpleTableHeaderAdapter simpleTableHeaderAdapter = new SimpleTableHeaderAdapter(context, "Period", "Amount", "Status");
        simpleTableHeaderAdapter.setTextColor(ContextCompat.getColor(context, R.color.table_header_text));
        setHeaderAdapter(simpleTableHeaderAdapter);

        final int rowColorEven = ContextCompat.getColor(context, R.color.table_data_row_even);
        final int rowColorOdd = ContextCompat.getColor(context, R.color.table_data_row_odd);
        setDataRowBackgroundProvider(TableDataRowBackgroundProviders.alternatingRowColors(rowColorEven, rowColorOdd));
        setHeaderSortStateViewProvider(SortStateViewProviders.brightArrows());

        final TableColumnWeightModel tableColumnWeightModel = new TableColumnWeightModel(3);
        tableColumnWeightModel.setColumnWeight(0, 3);
        tableColumnWeightModel.setColumnWeight(1, 2);
        tableColumnWeightModel.setColumnWeight(2, 3);
        setColumnModel(tableColumnWeightModel);

        setColumnComparator(0, BillComparators.getDonemComparator());
        setColumnComparator(1, BillComparators.getTutarComparator());
        setColumnComparator(2, BillComparators.getDurumComparator());
    }

}
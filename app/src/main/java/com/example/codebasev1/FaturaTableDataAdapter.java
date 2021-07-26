package com.example.codebasev1;


import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.LongPressAwareTableDataAdapter;
import com.example.codebasev1.data.Fatura;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import static java.lang.String.format;

public class FaturaTableDataAdapter extends LongPressAwareTableDataAdapter<Fatura> {

    private static final int TEXT_SIZE = 14;
    private static final NumberFormat PRICE_FORMATTER = NumberFormat.getNumberInstance();


    public FaturaTableDataAdapter(final Context context, final List<Fatura> data, final TableView<Fatura> tableView) {
        super(context, data, tableView);
    }

    @Override
    public View getDefaultCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
        final Fatura fatura = getRowData(rowIndex);
        View renderedView = null;

        switch (columnIndex) {
            case 0:
                renderedView = renderDonem(fatura);
                break;
            case 1:
                renderedView = renderTutar(fatura);
                break;
            case 2:
                renderedView = renderDurum(fatura, parentView);
                break;
        }

        return renderedView;
    }

    @Override
    public View getLongPressCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
        final Fatura fatura = getRowData(rowIndex);
        View renderedView = null;

        switch (columnIndex) {
            case 1:
                renderedView = renderEditableDonem(fatura);
                break;
            default:
                renderedView = getDefaultCellView(rowIndex, columnIndex, parentView);
        }

        return renderedView;
    }

    private View renderEditableDonem(final Fatura fatura) {
        final EditText editText = new EditText(getContext());
        editText.setText(fatura.getPeriod());
        editText.setPadding(20, 10, 20, 10);
        editText.setTextSize(TEXT_SIZE);
        editText.setSingleLine();
        return editText;
    }

    private View renderTutar(final Fatura fatura) {
        final String priceString = PRICE_FORMATTER.format(fatura.getAmount()) + " TL";

        final TextView textView = new TextView(getContext());
        textView.setText(priceString);
        textView.setPadding(20, 10, 20, 10);
        textView.setTextSize(TEXT_SIZE);

        /*if (fatura.getTutar() < 50) {
            textView.setTextColor(ContextCompat.getColor(getContext(), R.color.table_price_low));
        } else if (fatura.getTutar() > 100) {
            textView.setTextColor(ContextCompat.getColor(getContext(), R.color.table_price_high));
        }
        else*/
            textView.setTextColor(ContextCompat.getColor(getContext(), R.color.table_header_text));

        return textView;
    }

    private View renderDurum(final Fatura fatura, final ViewGroup parentView) {

        final TextView textView = new TextView(getContext());
        textView.setText(format(Locale.ENGLISH, "%s", fatura.getStatus()));
        textView.setPadding(20, 10, 20, 10);
        textView.setTextSize(TEXT_SIZE);

        if (fatura.getStatus().equals("Paid")) {
            textView.setTextColor(ContextCompat.getColor(getContext(), R.color.table_header_text));
        } else {
            textView.setTextColor(ContextCompat.getColor(getContext(), R.color.table_price_high));
        }

        return textView;
    }

    private View renderDonem(final Fatura fatura) {

        final TextView textView = new TextView(getContext());
        textView.setText(format(Locale.ENGLISH, "%s", fatura.getPeriod()));
        textView.setPadding(20, 10, 20, 10);
        textView.setTextSize(TEXT_SIZE);
        textView.setTextColor(ContextCompat.getColor(getContext(), R.color.table_header_text));


        return textView;
    }

    private View renderString(final String value) {
        final TextView textView = new TextView(getContext());
        textView.setText(value);
        textView.setPadding(20, 10, 20, 10);
        textView.setTextSize(TEXT_SIZE);
        return textView;
    }

    private static class FaturaDonemUpdater implements TextWatcher {

        private Fatura faturaToUpdate;

        public FaturaDonemUpdater(Fatura faturaToUpdate) {
            this.faturaToUpdate = faturaToUpdate;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // no used
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // not used
        }

        @Override
        public void afterTextChanged(Editable s) {
            faturaToUpdate.setPeriod(s.toString());
        }
    }
}
package ru.trofimov.views;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import ru.trofimov.models.ConversionHistory;
import ru.trofimov.service.HistoryService;
import ru.trofimov.service.HistoryServiceImpl;

import java.util.List;

public class History extends WebPage {
    public History() {
        HistoryService service = new HistoryServiceImpl();
        List<ConversionHistory> list = service.findAll();

        add(new ListView<ConversionHistory>("listView", list) {
            public void populateItem(final ListItem<ConversionHistory> item) {
                final ConversionHistory data = item.getModelObject();
                item.add(new Label("date", data.getStringDate()));
                item.add(new Label("original", data.getForHistoryTable(true)));
                item.add(new Label("result", data.getForHistoryTable(false)));
            }
        });
    }
}

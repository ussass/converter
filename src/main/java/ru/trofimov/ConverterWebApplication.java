package ru.trofimov;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.settings.MarkupSettings;
import ru.trofimov.views.Index;
import ru.trofimov.views.History;

public class ConverterWebApplication extends WebApplication {

    @Override
    public Class getHomePage() {
        return Index.class;
    }

    @Override
    protected void init() {
        super.init();
        mountPage("history", History.class);
    }

    @Override
    public MarkupSettings getMarkupSettings() {
        return new MarkupSettings().setDefaultMarkupEncoding("UTF-8");
    }
}

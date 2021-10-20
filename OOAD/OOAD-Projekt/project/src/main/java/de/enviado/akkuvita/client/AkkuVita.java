/*
 * Copyright 2010 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package de.enviado.akkuvita.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.google.gwt.dev.shell.remoteui.RemoteMessageProto;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.TimeZone;
import com.google.gwt.i18n.client.constants.TimeZoneConstants;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import de.enviado.akkuvita.shared.AkkuDefekt;
import de.enviado.akkuvita.shared.AkkuVitaRequestFactory;
import de.enviado.akkuvita.shared.proxy.*;
import de.enviado.akkuvita.shared.service.AkkuEventRequest;
import de.enviado.akkuvita.shared.service.AkkuRequest;
import de.enviado.akkuvita.shared.service.KundeRequest;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The entry point class which performs the initial loading of the AkkuVita
 * application.
 */
public class AkkuVita implements EntryPoint {

    private static final Logger log = Logger.getLogger(AkkuVita.class.getName());

    private EventBus eventBus = new SimpleEventBus();
    private AkkuVitaRequestFactory requests;
    private TimeZone timeZone;

    /**
     * This method sets up the top-level services used by the application.
     */
    public void onModuleLoad() {
        // Setup Exception Handling
        GWT.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
            public void onUncaughtException(Throwable e) {
                log.log(Level.SEVERE, e.getMessage(), e);
            }
        });

        // Setup RequestFactory
        requests = GWT.create(AkkuVitaRequestFactory.class);
        requests.initialize(eventBus);

        TimeZoneConstants timeZoneConstants = GWT.create(TimeZoneConstants.class);
        timeZone = TimeZone.createTimeZone(timeZoneConstants.europeBerlin());


        initTabPanel(RootPanel.get());

        // Fast test to see if the sample is not being run from devmode
        if (GWT.getHostPageBaseURL().startsWith("file:")) {
            log.log(Level.SEVERE,
                    "The AkkuVita cannot be run without its"
                            + " server component.  If you are running the sample from a"
                            + " GWT distribution, use the 'ant devmode' target to launch" + " the Server.");
        }
    }

    private void initTabPanel(HasWidgets parent) {
        TabPanel panel = new TabPanel();
        panel.setHeight("100%");
        panel.setWidth("100%");

        initAkkuListPanel(panel);
        initKundenListPanel(panel);
        initNeuerEintragPanel(panel);

        panel.selectTab(0);
        parent.add(panel);
    }

    private void initAkkuListPanel(TabPanel parent) {
        VerticalPanel panel = new VerticalPanel();
        AsyncDataProvider<AkkuProxy> akkuDataProvider = new AkkuDataProvider(requests);

        CellTable<AkkuProxy> akkuTable = new CellTable<AkkuProxy>();

        akkuTable.addColumn(new TextColumn<AkkuProxy>() {
            @Override
            public String getValue(AkkuProxy object) {
                return object.getSeriennummer();
            }
        }, "Seriennummer");

        akkuTable.addColumn(new TextColumn<AkkuProxy>() {
            @Override
            public String getValue(AkkuProxy object) {
                if (object.getProduktionsdatum() == null) {
                    return "";
                }
                return DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_SHORT).format(object.getProduktionsdatum(), timeZone);
            }
        }, "Produktionsdatum");

        akkuTable.addColumn(new TextColumn<AkkuProxy>() {
            @Override
            public String getValue(AkkuProxy object) {
                if (object.getReperaturanzahl() == null) {
                    return "";
                }
                return object.getReperaturanzahl().toString();
            }
        }, "Reperaturanzahl");

        akkuTable.addColumn(new TextColumn<AkkuProxy>() {
            @Override
            public String getValue(AkkuProxy object) {
                return "";
            }
        }, "Letztes Ereignis");

        SingleSelectionModel<AkkuProxy> selectionModel = new SingleSelectionModel<>();
        selectionModel.addSelectionChangeHandler(event -> {
            AkkuProxy akkuProxy = selectionModel.getSelectedObject();
            initDetailWinow(parent, akkuProxy);
        });
        akkuTable.setSelectionModel(selectionModel);


        panel.add(akkuTable);
        akkuDataProvider.addDataDisplay(akkuTable);
        akkuTable.setVisibleRange(0, 50);


        SimplePager pager = new SimplePager();
        pager.setDisplay(akkuTable);
        panel.add(pager);

        parent.add(panel, "Akkus");
    }

    private void initKundenListPanel(TabPanel parent) {
        VerticalPanel panel = new VerticalPanel();
        KundenDataProvider kundenDataProvider = new KundenDataProvider(requests);
        CellTable<KundeProxy> kundenTable = new CellTable<KundeProxy>();


        kundenTable.addColumn(new TextColumn<KundeProxy>() {
            @Override
            public String getValue(KundeProxy object) {
                return object.getKundennummer().toString();
            }
        }, "Kundennummer");

        kundenTable.addColumn(new TextColumn<KundeProxy>() {
            @Override
            public String getValue(KundeProxy object) {
                return object.getName();
            }
        }, "Name");

        kundenTable.addColumn(new TextColumn<KundeProxy>() {
            @Override
            public String getValue(KundeProxy object) {
                return object.getFirma();
            }
        }, "Firma");

        SingleSelectionModel<KundeProxy> selectionModel = new SingleSelectionModel<>();
        selectionModel.addSelectionChangeHandler(event -> {
            KundeProxy kundeProxy = selectionModel.getSelectedObject();
            initDetailWinow(parent, kundeProxy);
        });
        kundenTable.setSelectionModel(selectionModel);

        panel.add(kundenTable);
        kundenDataProvider.addDataDisplay(kundenTable);
        kundenTable.setVisibleRange(0, 50);

        SimplePager pager2 = new SimplePager();
        pager2.setDisplay(kundenTable);
        panel.add(pager2);
        parent.add(panel, "Kunden");
    }

    private void initNeuerEintragPanel(TabPanel parent) {
        TabPanel tabPanel = new TabPanel();

        initNeuesEreignisPanel(tabPanel);
        initNeuePruefung(tabPanel);
        initNeuerAkkuPanel(tabPanel);

        initNeuerKundePanel(tabPanel);

        tabPanel.selectTab(0);
        parent.add(tabPanel, "Neuer Eintrag");
    }

    private void initNeuesEreignisPanel(TabPanel parent) {
        VerticalPanel panel = new VerticalPanel();
        Grid grid = new Grid(4, 2);

        grid.setWidget(0, 0, new Label("Ereignis"));
        ListBox typeSelectBox = new ListBox();
        typeSelectBox.addItem("Akku Ausmustern");
        typeSelectBox.addItem("Akku wird zur Reparatur verschickt");
        typeSelectBox.addItem("Akku is von der Reparatur zurückgekommen");
        grid.setWidget(0, 1, typeSelectBox);

        grid.setWidget(1, 0, new Label("Akku"));

        AkkuSuggestOracle suggestOracle = new AkkuSuggestOracle();

        SuggestBox akkuSuggestBox = new SuggestBox(suggestOracle);
        requests.akkuRequest().findAllAkkus().fire(new Receiver<List<AkkuProxy>>() {
            @Override
            public void onSuccess(List<AkkuProxy> response) {
                suggestOracle.setAkkuProxyList(response);
            }
        });

        grid.setWidget(1, 1, akkuSuggestBox);

        grid.setWidget(2, 0, new Label("Datum"));
        DatePicker datePicker = new DatePicker();
        grid.setWidget(2, 1, datePicker);

        grid.setWidget(3, 0, new Label("Notiz"));
        TextArea noteArea = new TextArea();
        noteArea.setCharacterWidth(100);
        noteArea.setVisibleLines(20);
        grid.setWidget(3, 1, noteArea);

        panel.add(grid);

        Button submitButton = new SubmitButton("Speichern");
        panel.add(submitButton);

        submitButton.addClickHandler(event -> {
            requests.akkuRequest().findAkku(akkuSuggestBox.getValue()).fire(new Receiver<AkkuProxy>() {
                @Override
                public void onSuccess(AkkuProxy akkuProxy) {
                    if (akkuProxy != null) {
                        switch (typeSelectBox.getSelectedIndex()) {
                            case 0:
                                createAusmusterungsEvent(akkuProxy, noteArea.getValue(), datePicker.getHighlightedDate());
                                break;
                            case 1:
                                createAusgangsEvent(akkuProxy, noteArea.getValue(), datePicker.getHighlightedDate());
                                break;
                            case 2:
                                createEingangsEvent(akkuProxy, noteArea.getValue(), datePicker.getHighlightedDate());
                                break;
                        }
                    }
                }


            });
        });

        parent.add(panel, "Neues Ereignis");
    }

    private void createAusmusterungsEvent(AkkuProxy akkuProxy, String notiz, Date date) {
        AkkuEventRequest context = requests.akkuEventRequest();
        AkkuProxy proxy = context.edit(akkuProxy);
        AusmusterungsEventProxy ausmusterungsEvent = context.create(AusmusterungsEventProxy.class);
        ausmusterungsEvent.setAkku(akkuProxy);
        ausmusterungsEvent.setDate(date);
        ausmusterungsEvent.setNotiz(notiz);
        context.persist().using(ausmusterungsEvent).fire();
    }

    private void createAusgangsEvent(AkkuProxy akkuProxy, String notiz, Date date) {
        AkkuEventRequest context = requests.akkuEventRequest();
        AkkuProxy proxy = context.edit(akkuProxy);
        ReparaturAusgangsEventProxy ausgangsEvent = context.create(ReparaturAusgangsEventProxy.class);
        ausgangsEvent.setAkku(akkuProxy);
        ausgangsEvent.setDate(date);
        ausgangsEvent.setNotiz(notiz);
        context.persist().using(ausgangsEvent).fire();
    }

    private void createEingangsEvent(AkkuProxy akkuProxy, String notiz, Date date) {
        AkkuEventRequest context = requests.akkuEventRequest();
        AkkuProxy proxy = context.edit(akkuProxy);
        ReparaturEingangsEventProxy eingangsEvent = context.create(ReparaturEingangsEventProxy.class);
        eingangsEvent.setAkku(akkuProxy);
        eingangsEvent.setDate(date);
        eingangsEvent.setNotiz(notiz);
        context.persist().using(eingangsEvent).fire();
    }

    private void initNeuePruefung(TabPanel parent) {
        VerticalPanel panel = new VerticalPanel();

        Grid grid = new Grid(8, 2);

        grid.setWidget(0, 0, new Label("Akku"));
        AkkuSuggestOracle akkuSuggestOracle = new AkkuSuggestOracle();
        SuggestBox akkuSuggestBox = new SuggestBox(akkuSuggestOracle);
        requests.akkuRequest().findAllAkkus().fire(new Receiver<List<AkkuProxy>>() {
            @Override
            public void onSuccess(List<AkkuProxy> response) {
                akkuSuggestOracle.setAkkuProxyList(response);
            }
        });
        grid.setWidget(0, 1, akkuSuggestBox);

        grid.setWidget(1, 0, new Label("Kunde"));
        KundeSuggestOracle kundeSuggestOracle = new KundeSuggestOracle();
        SuggestBox kundeSuggestBox = new SuggestBox();
        requests.kundeRequest().findAllKunden().fire(new Receiver<List<KundeProxy>>() {
            @Override
            public void onSuccess(List<KundeProxy> response) {
                log.info("found Kunden " + String.valueOf(response.size()));
                kundeSuggestOracle.setKunden(response);
            }
        });
        grid.setWidget(1, 1, kundeSuggestBox);

        grid.setWidget(2, 0, new Label("Datum"));
        DatePicker datePicker = new DatePicker();
        grid.setWidget(2, 1, datePicker);

        grid.setWidget(3,0,new Label("Kapazität"));
        TextBox kapazitaetTextBox = new TextBox();
        grid.setWidget(3,1,kapazitaetTextBox);

        grid.setWidget(4,1,new Label("Ladezyklen"));
        TextBox ladezyklenTextBox = new TextBox();
        grid.setWidget(4,1,ladezyklenTextBox);

        grid.setWidget(5,1,new Label("Ticketnummer"));
        TextBox ticketNrTextBox = new TextBox();
        grid.setWidget(5,1,ticketNrTextBox);

        grid.setWidget(6,1,new Label("Fehler"));
        ListBox fehlerListBox = new ListBox();
        for(AkkuDefekt defekt: AkkuDefekt.values()){
            fehlerListBox.addItem(defekt.description);
        }
        grid.setWidget(6,1,fehlerListBox);

        grid.setWidget(7, 0, new Label("Notiz"));
        TextArea noteArea = new TextArea();
        noteArea.setCharacterWidth(100);
        noteArea.setVisibleLines(20);
        grid.setWidget(7, 1, noteArea);

        panel.add(grid);

        Button submitButton = new SubmitButton("Speichern");

        submitButton.addClickHandler(event -> {
            requests.kundeRequest().findKunde(Integer.parseInt(kundeSuggestBox.getValue())).fire(new Receiver<KundeProxy>() {
                @Override
                public void onSuccess(KundeProxy kundeProxy) {
                    requests.akkuRequest().findAkku(akkuSuggestBox.getValue()).fire(new Receiver<AkkuProxy>() {
                        @Override
                        public void onSuccess(AkkuProxy akkuProxy) {
                            createPruefungsEvent(
                                    akkuProxy,
                                    kundeProxy,
                                    datePicker.getHighlightedDate(),
                                    Float.parseFloat(kapazitaetTextBox.getValue()),
                                    Integer.parseInt(ladezyklenTextBox.getValue()),
                                    Integer.parseInt(ticketNrTextBox.getValue()),
                                    AkkuDefekt.values()[fehlerListBox.getSelectedIndex()]
                            );
                        }
                    });
                }
            });
        });

        panel.add(submitButton);
        parent.add(panel, "Neue Prüfung");
    }

    private void createPruefungsEvent(AkkuProxy akkuProxy, KundeProxy kundeProxy, Date date, Float kapazitaet, Integer ladezyklen, Integer ticketnr, AkkuDefekt defekt){
        AkkuEventRequest context = requests.akkuEventRequest();
        AkkuProxy akku = context.edit(akkuProxy);
        KundeProxy kunde = context.edit(kundeProxy);
        AkkuPruefungsEventProxy akkuPruefungsEvent = context.create(AkkuPruefungsEventProxy.class);
        akkuPruefungsEvent.setAkku(akku);
        akkuPruefungsEvent.setKunde(kunde);
        akkuPruefungsEvent.setLadezyklen(ladezyklen);
        akkuPruefungsEvent.setDate(date);
        akkuPruefungsEvent.setKapazitaet(kapazitaet);
        akkuPruefungsEvent.setTicketnr(ticketnr);
        akkuPruefungsEvent.setDefekt(defekt);
        context.persist().using(akkuPruefungsEvent).fire();
    }

    private void initNeuerAkkuPanel(TabPanel parent) {
        VerticalPanel panel = new VerticalPanel();
        Grid grid = new Grid(3, 2);
        panel.add(grid);
        grid.setWidget(0, 0, new Label("Seriennummer"));
        TextBox seriennummerTextBox = new TextBox();
        grid.setWidget(0, 1, seriennummerTextBox);

        grid.setWidget(1, 0, new Label("Produktionsdatum"));
        DatePicker produktionsdatumDatePicker = new DatePicker();
        grid.setWidget(1, 1, produktionsdatumDatePicker);

        grid.setWidget(2, 0, new Label("Reperaturanzahl"));
        TextBox repatraturanzahlTextBox = new TextBox();
        grid.setWidget(2, 1, repatraturanzahlTextBox);

        parent.add(panel, "Neuer Akku");
        Button submitButton = new Button("Submit");
        panel.add(submitButton);

        submitButton.addClickHandler(event -> {
            AkkuRequest context = requests.akkuRequest();
            AkkuProxy akku = context.create(AkkuProxy.class);
            akku.setSeriennummer(seriennummerTextBox.getValue());
            akku.setReperaturanzahl(Integer.valueOf(repatraturanzahlTextBox.getValue()));
            akku.setProduktionsdatum(produktionsdatumDatePicker.getHighlightedDate());
            seriennummerTextBox.setReadOnly(true);
            repatraturanzahlTextBox.setReadOnly(true);
            context.persist().using(akku).fire(new Receiver<Void>() {
                @Override
                public void onSuccess(Void response) {
                    seriennummerTextBox.setValue("");
                    repatraturanzahlTextBox.setValue("");
                    seriennummerTextBox.setReadOnly(false);
                    repatraturanzahlTextBox.setReadOnly(false);
                    PopupPanel popupPanel = new PopupPanel(true);
                    popupPanel.add(new Label("Success"));
                    popupPanel.show();
                }

                @Override
                public void onFailure(ServerFailure error) {
                    seriennummerTextBox.setReadOnly(false);
                    repatraturanzahlTextBox.setReadOnly(false);
                    PopupPanel popupPanel = new PopupPanel(true);
                    popupPanel.add(new Label("Failed"));
                    popupPanel.show();
                }
            });
        });
    }

    private void initNeuerKundePanel(TabPanel parent) {
        VerticalPanel panel = new VerticalPanel();
        parent.add(panel, "Neuer Kunde");
        Grid grid = new Grid(3, 2);
        panel.add(grid);

        grid.setWidget(0, 0, new Label("Kundennummer"));
        TextBox kundennummerTextBox = new TextBox();
        grid.setWidget(0, 1, kundennummerTextBox);

        grid.setWidget(1, 0, new Label("Name"));
        TextBox nameTextBox = new TextBox();
        grid.setWidget(1, 1, nameTextBox);

        grid.setWidget(2, 0, new Label("Firmenname"));
        TextBox firmennameTextBox = new TextBox();
        grid.setWidget(2, 1, firmennameTextBox);

        Button submitButton = new Button("Speichern");
        panel.add(submitButton);
        submitButton.addClickHandler(event -> {
            KundeRequest context = requests.kundeRequest();
            KundeProxy kunde = context.create(KundeProxy.class);
            kunde.setKundennummer(Integer.valueOf(kundennummerTextBox.getValue()));
            kunde.setName(nameTextBox.getValue());
            kunde.setFirma(firmennameTextBox.getValue());
            kundennummerTextBox.setReadOnly(true);
            nameTextBox.setReadOnly(true);
            firmennameTextBox.setReadOnly(true);
            context.persist().using(kunde).fire(new Receiver<Void>() {
                @Override
                public void onSuccess(Void response) {
                    kundennummerTextBox.setValue("");
                    nameTextBox.setValue("");
                    firmennameTextBox.setValue("");
                    kundennummerTextBox.setReadOnly(false);
                    nameTextBox.setReadOnly(false);
                    firmennameTextBox.setReadOnly(false);
                    PopupPanel popupPanel = new PopupPanel(true);
                    popupPanel.add(new Label("Success"));
                    popupPanel.show();
                }

                @Override
                public void onFailure(ServerFailure error) {
                    kundennummerTextBox.setReadOnly(false);
                    nameTextBox.setReadOnly(false);
                    firmennameTextBox.setReadOnly(false);
                    PopupPanel popupPanel = new PopupPanel(true);
                    popupPanel.add(new Label("Failed"));
                    popupPanel.show();
                }
            });
        });
    }

    public void initDetailWinow(TabPanel parent, AkkuProxy proxy) {
        VerticalPanel panel = new VerticalPanel();

        Grid grid = new Grid(3, 2);
        panel.add(grid);
        grid.setWidget(0, 0, new Label("Seriennummer"));
        TextBox seriennummerTextBox = new TextBox();
        seriennummerTextBox.setValue(proxy.getSeriennummer());
        grid.setWidget(0, 1, seriennummerTextBox);

        grid.setWidget(1, 0, new Label("Produktionsdatum"));
        DatePicker produktionsdatumDatePicker = new DatePicker();
        produktionsdatumDatePicker.setValue(proxy.getProduktionsdatum());
        grid.setWidget(1, 1, produktionsdatumDatePicker);

        grid.setWidget(2, 0, new Label("Reperaturanzahl"));
        TextBox repatraturanzahlTextBox = new TextBox();
        repatraturanzahlTextBox.setValue(proxy.getReperaturanzahl().toString());
        grid.setWidget(2, 1, repatraturanzahlTextBox);

        Button submitButton = new Button("Speichern");
        panel.add(submitButton);

        submitButton.addClickHandler(event -> {
            AkkuRequest context = requests.akkuRequest();
            AkkuProxy akku = context.create(AkkuProxy.class);
            akku.setSeriennummer(seriennummerTextBox.getValue());
            akku.setReperaturanzahl(Integer.valueOf(repatraturanzahlTextBox.getValue()));
            akku.setProduktionsdatum(produktionsdatumDatePicker.getHighlightedDate());
            seriennummerTextBox.setReadOnly(true);
            repatraturanzahlTextBox.setReadOnly(true);
            context.persist().using(akku).fire(new Receiver<Void>() {
                @Override
                public void onSuccess(Void response) {
                    seriennummerTextBox.setValue("");
                    repatraturanzahlTextBox.setValue("");
                    seriennummerTextBox.setReadOnly(false);
                    repatraturanzahlTextBox.setReadOnly(false);
                    PopupPanel popupPanel = new PopupPanel(true);
                    popupPanel.add(new Label("Success"));
                    popupPanel.show();
                }

                @Override
                public void onFailure(ServerFailure error) {
                    seriennummerTextBox.setReadOnly(false);
                    repatraturanzahlTextBox.setReadOnly(false);
                    PopupPanel popupPanel = new PopupPanel(true);
                    popupPanel.add(new Label("Failed"));
                    popupPanel.show();
                }
            });
        });


        CellTable<AkkuEventProxy> akkuEventCellTable = new CellTable<AkkuEventProxy>();

        akkuEventCellTable.addColumn(new TextColumn<AkkuEventProxy>() {
            @Override
            public String getValue(AkkuEventProxy object) {
                if (object instanceof ReparaturEingangsEventProxy) {
                    return "Von der Reparatur eingetroffen";
                }
                if (object instanceof ReparaturAusgangsEventProxy) {
                    return "Zur Reparatur rausgegangen";
                }
                if (object instanceof AusmusterungsEventProxy) {
                    return "Ausgemustert";
                }
                if (object instanceof AkkuPruefungsEventProxy) {
                    return "Geprüft";
                }
                return "UNKOWN";
            }
        });

        requests.akkuEventRequest().findAkkuEvent(proxy).fire(new Receiver<List<AkkuEventProxy>>() {
            @Override
            public void onSuccess(List<AkkuEventProxy> response) {
                akkuEventCellTable.setRowData(response);
            }
        });

        panel.add(akkuEventCellTable);
        panel.add(akkuEventCellTable);

        Button closeButton = new Button("close");
        closeButton.addClickHandler(event -> {
            parent.remove(panel);
            parent.selectTab(0);
        });
        panel.add(closeButton);
        parent.add(panel, proxy.getSeriennummer());
        parent.selectTab(parent.getWidgetIndex(panel));
    }

    public void initDetailWinow(TabPanel parent, KundeProxy proxy) {
        VerticalPanel panel = new VerticalPanel();
        Grid grid = new Grid(3, 2);
        panel.add(grid);

        grid.setWidget(0, 0, new Label("Kundennummer"));
        TextBox kundennummerTextBox = new TextBox();
        kundennummerTextBox.setValue(proxy.getKundennummer().toString());
        grid.setWidget(0, 1, kundennummerTextBox);

        grid.setWidget(1, 0, new Label("Name"));
        TextBox nameTextBox = new TextBox();
        nameTextBox.setText(proxy.getName());
        grid.setWidget(1, 1, nameTextBox);

        grid.setWidget(2, 0, new Label("Firmenname"));
        TextBox firmennameTextBox = new TextBox();
        grid.setWidget(2, 1, firmennameTextBox);

        Button updateButton = new Button("Speichern");
        Button closeButton = new Button("Schließen");

        updateButton.addClickHandler(event -> {
            KundeRequest context = requests.kundeRequest();
            KundeProxy kunde = context.create(KundeProxy.class);
            kunde.setKundennummer(Integer.valueOf(kundennummerTextBox.getValue()));
            kunde.setName(nameTextBox.getValue());
            kunde.setFirma(firmennameTextBox.getValue());
            kundennummerTextBox.setReadOnly(true);
            nameTextBox.setReadOnly(true);
            firmennameTextBox.setReadOnly(true);
            context.persist().using(kunde).fire(new Receiver<Void>() {
                @Override
                public void onSuccess(Void response) {
                    kundennummerTextBox.setValue("");
                    nameTextBox.setValue("");
                    firmennameTextBox.setValue("");
                    kundennummerTextBox.setReadOnly(false);
                    nameTextBox.setReadOnly(false);
                    firmennameTextBox.setReadOnly(false);
                    PopupPanel popupPanel = new PopupPanel(true);
                    popupPanel.add(new Label("Success"));
                    popupPanel.show();
                }

                @Override
                public void onFailure(ServerFailure error) {
                    kundennummerTextBox.setReadOnly(false);
                    nameTextBox.setReadOnly(false);
                    firmennameTextBox.setReadOnly(false);
                    PopupPanel popupPanel = new PopupPanel(true);
                    popupPanel.add(new Label("Failed"));
                    popupPanel.show();
                }
            });
        });
        closeButton.addClickHandler(event -> {
            parent.remove(panel);
            parent.selectTab(0);
        });

        panel.add(updateButton);
        panel.add(closeButton);

        parent.add(panel, proxy.getKundennummer().toString());
        parent.selectTab(parent.getWidgetIndex(panel));
    }

}

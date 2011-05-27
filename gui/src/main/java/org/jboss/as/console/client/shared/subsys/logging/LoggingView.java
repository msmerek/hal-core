/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This copyrighted material is made available to anyone wishing to use,
 * modify, copy, or redistribute it subject to the terms and conditions
 * of the GNU Lesser General Public License, v. 2.1.
 * This program is distributed in the hope that it will be useful, but WITHOUT A
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public License,
 * v.2.1 along with this distribution; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA  02110-1301, USA.
 */

package org.jboss.as.console.client.shared.subsys.logging;

import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import org.jboss.as.console.client.Console;
import org.jboss.as.console.client.core.DisposableViewImpl;
import org.jboss.as.console.client.shared.subsys.logging.model.LoggingHandler;
import org.jboss.as.console.client.widgets.RHSContentPanel;

import java.util.List;

/**
 * @author Stan Silvert
 * @date 3/29/11
 */
public class LoggingView extends DisposableViewImpl implements LoggingPresenter.MyView {

    private LoggingPresenter presenter;
    private LoggingEditor loggingEditor;

    @Override
    public Widget createWidget() {

        LayoutPanel layout = new RHSContentPanel(Console.CONSTANTS.subsys_logging_logging());

        loggingEditor = new LoggingEditor(presenter);
        layout.add(loggingEditor.asWidget());

        return layout;
    }

    @Override
    public void setPresenter(LoggingPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void updateLoggingInfo(LoggingInfo loggingInfo) {
        loggingEditor.updateLoggingHandlers(loggingInfo);
    }
}

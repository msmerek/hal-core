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

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import org.jboss.as.console.client.core.ManualRevealPresenter;
import org.jboss.as.console.client.core.NameTokens;
import org.jboss.as.console.client.shared.subsys.RevealStrategy;
import org.jboss.as.console.spi.RequiredResources;
import org.jboss.as.console.spi.SearchIndex;


/**
 * The Presenter for Logging
 * @author Stan Silvert ssilvert@redhat.com (C) 2011 Red Hat Inc.
 * @date 10/27/2011
 */
public class LoggingPresenter extends ManualRevealPresenter<LoggingPresenter.MyView, LoggingPresenter.MyProxy> {

    @ProxyCodeSplit
    @NameToken(NameTokens.Logger)
    @RequiredResources(resources = {"{selected.profile}/subsystem=logging"})
    @SearchIndex(keywords = {"log", "log-level", "category", "pattern", "handler", "log-file", "log4j"})
    public interface MyProxy extends ProxyPlace<LoggingPresenter> {
    }

    public interface MyView extends View {
        void initialLoad();
    }

    private final RevealStrategy revealStrategy;

    @Inject
    public LoggingPresenter(
            EventBus eventBus, MyView view, MyProxy proxy,
            RevealStrategy revealStrategy) {
        super(eventBus, view, proxy);

        this.revealStrategy = revealStrategy;
    }

    @Override
    protected void onBind() {
        super.onBind();
    }

    @Override
    protected void onReset() {
        super.onReset();
        getView().initialLoad();
    }

    @Override
    protected void revealInParent() {
        revealStrategy.revealInParent(this);
    }
}

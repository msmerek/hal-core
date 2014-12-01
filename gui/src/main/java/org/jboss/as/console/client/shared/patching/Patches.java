/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013 Red Hat Inc. and/or its affiliates and other contributors
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
package org.jboss.as.console.client.shared.patching;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Harald Pehl
 */
public class Patches implements Iterable<PatchInfo> {

    public final static String STANDALONE_HOST = "standalone";

    private final String host;
    private final List<PatchInfo> patches;
    private PatchInfo latest;
    private boolean restartRequired;

    public Patches() {
        this(STANDALONE_HOST);
    }

    public Patches(String host) {
        this.host = host;
        this.patches = new LinkedList<PatchInfo>();
        this.latest = null;
    }

    public void setLatest(String id) {
        if (id != null) {
            for (PatchInfo patch : patches) {
                if (id.equals(patch.getId())) {
                    latest = patch;
                    break;
                }
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patches)) return false;

        Patches that = (Patches) o;

        if (!host.equals(that.host)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return host.hashCode();
    }

    public String getHost() {
        return host;
    }

    public PatchInfo getLatest() {
        return latest;
    }

    @Override
    public Iterator<PatchInfo> iterator() {
        return patches.iterator();
    }

    public List<PatchInfo> asList() {return patches;}

    public boolean isEmpty() {return patches.isEmpty();}

    public boolean add(final PatchInfo patchInfo) {return patches.add(patchInfo);}

    public PatchInfo find(final String patchId) {
        for (PatchInfo patchInfo : this) {
            if (patchInfo.getId().equals(patchId)) {
                return patchInfo;
            }
        }
        return null;
    }

    public boolean isRestartRequired() {
        return restartRequired;
    }

    public void setRestartRequired(final boolean restartRequired) {
        this.restartRequired = restartRequired;
    }
}

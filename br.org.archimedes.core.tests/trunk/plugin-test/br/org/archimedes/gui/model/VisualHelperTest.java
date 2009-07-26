/**
 * Copyright (c) 2006, 2009 Hugo Corbucci and others.<br>
 * All rights reserved. This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html<br>
 *<br>
 * Contributors:<br>
 * Luiz Real, Bruno da Hora - initial API and implementation<br>
 * <br>
 * This file was created on 23/06/2009, 06:11:00.<br>
 * It is part of br.org.archimedes.gui.model on the br.org.archimedes.core.tests project.<br>
 */

package br.org.archimedes.gui.model;

import br.org.archimedes.Tester;
import br.org.archimedes.controller.InputController;
import br.org.archimedes.factories.CommandFactory;
import br.org.archimedes.gui.opengl.OpenGLWrapper;
import br.org.archimedes.model.ReferencePoint;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Luiz Real, Bruno da Hora
 */
public class VisualHelperTest extends Tester {

    private OpenGLWrapper openGLWrapper;

    private Workspace workspace;

    private InputController inputController;

    private VisualHelper visualHelper;


    @Before
    public void setUp () {

        openGLWrapper = mock(OpenGLWrapper.class);
        workspace = mock(Workspace.class);
        inputController = mock(InputController.class);
        visualHelper = new VisualHelper(openGLWrapper, workspace, inputController);
    }

    @Test
    public void drawGripPointIfThereIsOne () {

        ReferencePoint gripPoint = mock(ReferencePoint.class);
        when(workspace.getGripMousePosition()).thenReturn(gripPoint);
        visualHelper.draw(false);
        verify(gripPoint).draw();
    }

    @Test
    public void drawCrossCursorIfNotTransformFactory () {

        CommandFactory activeFactory = mock(CommandFactory.class);
        when(inputController.getCurrentFactory()).thenReturn(activeFactory);
        when(activeFactory.isTransformFactory()).thenReturn(false);
        // TODO finish this test
    }

    // TODO Test drawing selection helper if selection is active

    // TODO Test drawing square cursor if it is a transform factory

    // TODO Test visual helper if there is an active factory
}
package ca.ulaval.glo4002.game.infrastructure.persistence.actions;

import ca.ulaval.glo4002.game.domain.actions.Action;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ActionRepositoryInMemoryTest {
    @InjectMocks
    private ActionRepositoryInMemory actionRepositoryInMemory;

    private List<Action> actualActions;

    @Mock
    private Action actionTurn;

    @Mock
    private Action actionRessource;

    @Mock
    private Action actionDino;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this.actualActions = new ArrayList<>();
    }

    @Test
    void havingAnAction_whenSaveThatAction_thenThatActionIsSaved() {
        this.actionRepositoryInMemory.save(this.actionTurn);

        this.actualActions = getActions();
        assertFalse(this.actualActions.isEmpty());
    }

    @Test
    void havingMultipleActions_whenExecuteThem_thenAllActionsAreExecuted() {
        setActionsRepository();
        InOrder inOrderActionTurn = Mockito.inOrder(this.actionTurn);
        InOrder inOrderActionDino = Mockito.inOrder(this.actionDino);
        InOrder inOrderActionRessource = Mockito.inOrder(this.actionRessource);

        this.actionRepositoryInMemory.executeActions();

        inOrderActionTurn.verify(this.actionTurn, Mockito.calls(1)).execute();
        inOrderActionDino.verify(this.actionDino, Mockito.calls(1)).execute();
        inOrderActionRessource.verify(this.actionRessource, Mockito.calls(1)).execute();
    }

    @Test
    void havingMultipleActions_whenExecuteThem_thenActionsAreCleared() {
        setActionsRepository();

        this.actionRepositoryInMemory.executeActions();

        this.actualActions = getActions();
        assertTrue(this.actualActions.isEmpty());
    }

    @Test
    void listOfActions_whenResetingActions_thenWaitingActionsListIsEmpty() {
        setActionsRepository();

        this.actionRepositoryInMemory.deleteAll();

        assertTrue(getActions().isEmpty());
    }

    private void setActionsRepository() {
        this.actionRepositoryInMemory.save(this.actionTurn);
        this.actionRepositoryInMemory.save(this.actionDino);
        this.actionRepositoryInMemory.save(this.actionRessource);
    }

    private List<Action> getActions() {
        return (List<Action>) Whitebox.getInternalState(this.actionRepositoryInMemory, "actionList");
    }
}
package ca.ulaval.glo4002.game.infrastructure.persistence.actions;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.reflection.Whitebox;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ca.ulaval.glo4002.game.domain.actions.Action;

class ActionRepositoryInMemoryTest {

    @InjectMocks
    private ActionRepositoryInMemory actionRepositoryInMemory;

    private List<Action> waitingActionsActual;

    @Mock
    private Action actionTurn;

    @Mock
    private Action actionRessource;

    @Mock
    private Action actionDino;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this.waitingActionsActual = new ArrayList<>();
    }

    @Test
    void whenSaveAnAction_thenActionListIsNotEmpty() {
        this.actionRepositoryInMemory.save(this.actionTurn);

        this.waitingActionsActual = getActions();
        assertFalse(this.waitingActionsActual.isEmpty());
    }

    @Test
    void listOfActions_whenExecute_thenAllActionsAreExecuted_and_waitingListActionsIcleared() {

        setActionsRepository();

        this.actionRepositoryInMemory.execute();

        InOrder inOrderActionTurn = Mockito.inOrder(this.actionTurn);
        InOrder inOrderActionDino = Mockito.inOrder(this.actionDino);
        InOrder inOrderActionRessource = Mockito.inOrder(this.actionRessource);

        inOrderActionTurn.verify(this.actionTurn, Mockito.calls(1)).execute();
        inOrderActionDino.verify(this.actionDino, Mockito.calls(1)).execute();
        inOrderActionRessource.verify(this.actionRessource, Mockito.calls(1)).execute();
        this.waitingActionsActual = getActions();
        assertTrue(this.waitingActionsActual.isEmpty());
    }

    @Test
    void listOfActions_whenResetingActions_thenWaitingActionsListIsEmpty() {
        setActionsRepository();

        this.actionRepositoryInMemory.reset();

        assertTrue(getActions().isEmpty());
    }

    private void setActionsRepository() {
        this.actionRepositoryInMemory.save(this.actionTurn);
        this.actionRepositoryInMemory.save(this.actionDino);
        this.actionRepositoryInMemory.save(this.actionRessource);
    }

    private List<Action> getActions() {
        return (List<Action>) Whitebox.getInternalState(this.actionRepositoryInMemory, "waitingActions");
    }
}
package ExpenseService;

import ExpenseService.Exception.UnexpectedProjectTypeException;
import ExpenseService.Expense.ExpenseType;
import ExpenseService.Project.Project;
import ExpenseService.Project.ProjectType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class ExpenseServiceTest {
    @Test
    void should_return_internal_expense_type_if_project_is_internal() throws UnexpectedProjectTypeException {
        // given
        Project internalProject= new Project(ProjectType.INTERNAL, "Internal Project");

        // when
        ExpenseType expensetype=ExpenseService.getExpenseCodeByProjectTypeAndName(internalProject);

        // then
        Assertions.assertEquals(ExpenseType.INTERNAL_PROJECT_EXPENSE,expensetype);
    }

    @Test
    void should_return_expense_type_A_if_project_is_external_and_name_is_project_A() throws UnexpectedProjectTypeException {
        // given
        Project externalProjectA= new Project(ProjectType.EXTERNAL, "Project A");

        // when
        ExpenseType expensetype=ExpenseService.getExpenseCodeByProjectTypeAndName(externalProjectA);

        // then
        Assertions.assertEquals(ExpenseType.EXPENSE_TYPE_A,expensetype);
    }

    @Test
    void should_return_expense_type_B_if_project_is_external_and_name_is_project_B() throws UnexpectedProjectTypeException {
        // given
        Project externalProjectB= new Project(ProjectType.EXTERNAL, "Project B");

        // when
        ExpenseType expensetype=ExpenseService.getExpenseCodeByProjectTypeAndName(externalProjectB);

        // then
        Assertions.assertEquals(ExpenseType.EXPENSE_TYPE_B,expensetype);
    }

    @Test
    void should_return_other_expense_type_if_project_is_external_and_has_other_name() throws UnexpectedProjectTypeException {
        // given
        Project externalProjectC= new Project(ProjectType.EXTERNAL, "Project C");

        // when
        ExpenseType expensetype=ExpenseService.getExpenseCodeByProjectTypeAndName(externalProjectC);

        // then
        Assertions.assertEquals(ExpenseType.OTHER_EXPENSE,expensetype);
    }

    @Test
    void should_throw_unexpected_project_exception_if_project_is_invalid() throws UnexpectedProjectTypeException {
        // given
        Project unexpectedProject= new Project(ProjectType.UNEXPECTED_PROJECT_TYPE, "Unexpected Project");

        // when
        UnexpectedProjectTypeException unexpectedProjectTypeException = Assertions.assertThrows(UnexpectedProjectTypeException.class, ()->{ ExpenseService.getExpenseCodeByProjectTypeAndName(unexpectedProject);});

        // then
        Assertions.assertEquals("You enter invalid project type",unexpectedProjectTypeException.getMessage());
    }
}
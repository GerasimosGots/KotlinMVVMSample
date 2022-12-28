package com.gerasimosGk.kotlinmvvmsample.useCase

import com.gerasimosGk.kotlinmvvmsample.*
import com.gerasimosGk.kotlinmvvmsample.base.MainCoroutineRule
import com.gerasimosGk.kotlinmvvmsample.base.getRawValue
import com.gerasimosGk.kotlinmvvmsample.base.runTestCoroutine
import com.gerasimosGk.kotlinmvvmsample.data.MockModelDataBuilder
import com.gerasimosGk.kotlinmvvmsample.data.model.api.DataResource
import com.gerasimosGk.kotlinmvvmsample.domain.UserModel
import com.gerasimosGk.kotlinmvvmsample.domain.UserUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class UserUseCaseTest {

    // Set the main coroutines dispatcher for unit testing.
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var useCase: UserUseCase

    // Object responsible return mock data
    private lateinit var mockDataBuilder : MockModelDataBuilder


    @Before
    fun setup() {
        // Start of each Test method -> Init Objects
        mockDataBuilder = MockModelDataBuilder()
    }

    @After
    fun tearDown() {
        // End of each Test -> Destroy objects
        Mockito.reset(useCase)
    }
    /**
     * -- STATE Tests
     */

    @Test
    fun `GIVEN user list, THEN the return model is UserModel`() {
        // Mock Data
        val mockData = mockDataBuilder.createMockUserModelList()
        val successData = DataResource.success(mockData)

        mainCoroutineRule.runTestCoroutine {
            // Mock return
            Mockito.`when`(useCase.getUserList()).thenReturn(successData)

            // GIVEN
            val actualModel: DataResource<MutableList<UserModel>> = useCase.getUserList()

            // THEN result type is UserModel
            Assert.assertTrue(actualModel.getRawValue() is MutableList<UserModel>)
        }
    }

    @Test
    fun `GIVEN user list, THEN the return model is not empty`() {
        // Mock Data
        val mockData = mockDataBuilder.createMockUserModelList()
        val successData = DataResource.success(mockData)

        mainCoroutineRule.runTestCoroutine {
            // Mock return
            Mockito.`when`(useCase.getUserList()).thenReturn(successData)

            // GIVEN
            val actualModelList = useCase.getUserList()

            // THEN result type is List of UserModel
            Assert.assertTrue(actualModelList.getRawValue()?.isNotEmpty() ?: false)
        }
    }

    @Test
    fun `GIVEN selecting user, THEN id is equals with what we set`() {
        // Mock Data
        val mockData = mockDataBuilder.createMockUserModelList()[0]
        val successData = DataResource.success(mockData)

        mainCoroutineRule.runTestCoroutine {
            // Mock return
            Mockito.`when`(useCase.getSelectedUser()).thenReturn(successData)

            val expectedId = "0"

            // GIVEN
            useCase.selectedUser(expectedId)
            val actualId = useCase.getSelectedUser().getRawValue()?.id ?: ""

            // THEN expected id is equals with actual id
            Assert.assertEquals(expectedId, actualId)
        }
    }

    @Test
    fun `GIVEN selecting user, THEN id is a not empty id`() {
        // Mock Data
        val mockData = mockDataBuilder.createMockUserModelList()[0]
        val successData = DataResource.success(mockData)

        mainCoroutineRule.runTestCoroutine {
            // Mock return
            Mockito.`when`(useCase.getSelectedUser()).thenReturn(successData)
            val expectedId = "0"

            // GIVEN
            useCase.selectedUser(expectedId)
            val actualId = useCase.getSelectedUser().getRawValue()?.id ?: ""

            // THEN expected id is not empty
            Assert.assertTrue(actualId.isNotEmpty())
        }
    }

    @Test
    fun `GIVEN selecting user, THEN mail is valid mail`() {
        // Mock Data
        val mockData = mockDataBuilder.createMockUserModelList()[0]
        val successData = DataResource.success(mockData)

        mainCoroutineRule.runTestCoroutine {
            // Mock return
            Mockito.`when`(useCase.getSelectedUser()).thenReturn(successData)

            // GIVEN
            val actualMail = useCase.getSelectedUser().getRawValue()?.email ?: ""

            // THEN expected mail is valid
            Assert.assertTrue(actualMail.isValidMail())
        }
    }

    @Test
    fun `GIVEN selecting user, THEN mail is not valid mail`() {
        // Mock Data
        val mockData = mockDataBuilder.createMockUserModelList()[1]
        val successData = DataResource.success(mockData)

        mainCoroutineRule.runTestCoroutine {

            // Mock return
            Mockito.`when`(useCase.getSelectedUser()).thenReturn(successData)

            // GIVEN
            val actualMail = useCase.getSelectedUser().getRawValue()?.email ?: ""

            // THEN expected mail is not valid
            Assert.assertFalse(actualMail.isValidMail())
        }
    }

    @Test
    fun `GIVEN selecting user, THEN phone is valid phone`() {
        // Mock Data
        val mockData = mockDataBuilder.createMockUserModelList()[0]
        val successData = DataResource.success(mockData)

        mainCoroutineRule.runTestCoroutine {

            // Mock return
            Mockito.`when`(useCase.getSelectedUser()).thenReturn(successData)

            // GIVEN
            val actualMail = useCase.getSelectedUser().getRawValue()?.phone ?: ""

            // THEN expected phone is valid
            Assert.assertTrue(actualMail.isValidPhone())
        }

    }

    @Test
    fun `GIVEN selecting user, THEN phone is not valid phone`() {
        // Mock Data
        val mockData = mockDataBuilder.createMockUserModelList()[1]
        val successData = DataResource.success(mockData)

        mainCoroutineRule.runTestCoroutine {

            // Mock return
            Mockito.`when`(useCase.getSelectedUser()).thenReturn(successData)

            // GIVEN
            val actualMail = useCase.getSelectedUser().getRawValue()?.phone ?: ""

            // THEN expected phone is not valid
            Assert.assertFalse(actualMail.isValidPhone())
        }
    }
}
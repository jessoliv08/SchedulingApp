import shared // This is your KMP shared module
import Foundation

@MainActor
class ViewModelProvider: ObservableObject {
    let selectDateViewModel: SelectDateViewModel
    let selectHourViewModel: SelectHourViewModel
    let setupInterviewedViewModel: SetupInterviewedViewModel
    let meetingInfoViewModel: MeetingInfoViewModel

    init() {
        let client = SharedScopeProvider.shared.createHttpClient() // Use helper in Kotlin to return a configured client
        let repository = AppointmentRepository(client: client)
        let meetingUseCase = MeetingUseCaseImpl(meetingRepository: MeetingStorage())
        let appointmentUseCase = AppointmentUseCaseImpl(appointmentRepository: repository)

        // If your view models need CoroutineScope, use MainScope (or pass as needed)
        let scope = SharedScopeProvider.shared.provideMainScope()

        self.selectDateViewModel = SelectDateViewModelImpl(scope: scope, appointmentUseCase: appointmentUseCase)
        self.selectHourViewModel = SelectHourViewModelImpl(scope: scope, appointmentUseCase: appointmentUseCase)
        self.setupInterviewedViewModel = SetupInterviewedViewModelImpl(scope: scope, meetingUseCase: meetingUseCase, appointmentUseCase: appointmentUseCase)
        self.meetingInfoViewModel = MeetingInfoViewModelImpl(scope: scope, meetingUseCase: meetingUseCase)
    }
}

import Foundation
import shared
import Combine

class SharedViewModelWrapper: ObservableObject {
    private let viewModel = ScheduleMeetingViewModel()
    
    @Published var selectedTime: String? = nil
    private var cancellable: AnyCancellable?
    private var timerCancellable: AnyCancellable?

    init() {
        // Use a timer to poll for updates from Kotlin's StateFlow
        cancellable = pollSelectedTime(viewModel)
            .receive(on: DispatchQueue.main)
            .sink { [weak self] time in
                self?.selectedTime = time
            }
    }

    func setSelectedTime(_ time: String) {
        viewModel.setSelectedTime(time: time)
    }

    private func pollSelectedTime(_ viewModel: ScheduleMeetingViewModel) -> AnyPublisher<String?, Never> {
        // Create a publisher that emits updates periodically via a timer
        Timer.publish(every: 1.0, on: .main, in: .common)
            .autoconnect()
            .flatMap { _ -> AnyPublisher<String?, Never> in
                // This is where we collect the current value from the Kotlin StateFlow
                Just(viewModel.selectedTime.value as? String)  // Get the current value from Kotlin StateFlow
                    .eraseToAnyPublisher()
            }
            .eraseToAnyPublisher()
    }
}

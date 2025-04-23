import SwiftUI

struct SetupInterviewedView: View {
    //@ObservedObject var viewModel: SharedViewModelWrapper
    @ObservedObject var viewModelProvider: ViewModelProvider
    @State private var navigate = false

    var body: some View {
        VStack {
            Text(viewModelProvider.setupInterviewedViewModel.interviewInfo)
                .font(.title)
            Spacer().frame(height: 20)
            VStack(alignment: .leading, spacing: 8) {
                IconTextView(iconText: viewModelProvider.setupInterviewedViewModel.timeInfo)
                IconTextView(iconText: viewModelProvider.setupInterviewedViewModel.callInfo)
                IconTextView(iconText: viewModelProvider.setupInterviewedViewModel.timeZone)
                Spacer().frame(height: 20)
                Divider()
                Text(viewModelProvider.setupInterviewedViewModel.enterDetails)
                    .font(.headline)
                Text(viewModelProvider.setupInterviewedViewModel.nameLabel)
                    .font(.body)
                Text(viewModelProvider.setupInterviewedViewModel.emailLabel)
                    .font(.body)
                Spacer()
                Text(viewModelProvider.setupInterviewedViewModel.descriptionAgree)
                    .font(.body)
                
            }.padding(.horizontal)
        }
        .navigationBarTitleDisplayMode(.inline)
    }
}

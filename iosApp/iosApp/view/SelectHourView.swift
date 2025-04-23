import SwiftUI

struct SelectHourView: View {
    //@ObservedObject var viewModel: SharedViewModelWrapper
    @ObservedObject var viewModelProvider: ViewModelProvider
    var body: some View {
        VStack {
            Text("Second Screen")
                .font(.largeTitle)
 //           Text("Selected Time: \(viewModel.selectedTime ?? "No time selected")")
        //        .padding()
        }
        .navigationTitle("Back")
        .navigationBarTitleDisplayMode(.inline)
    }
}

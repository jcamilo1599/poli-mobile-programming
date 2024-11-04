import SwiftUI
import Shared

struct ContentView: View {
    @State private var title: String = ""
    @State private var priority: String = "Select Priority"
    @State private var category: String = "Select Category"
    @State private var description: String = ""
    @State private var dueDate = Date()
    @State private var isDatePickerVisible: Bool = false
    @State private var notificationsEnabled: Bool = true
    
    var body: some View {
        ScrollView {
            VStack(spacing: 20) {
                TextField("Title", text: $title)
                    .padding()
                    .overlay(RoundedRectangle(cornerRadius: 8).stroke(.gray, lineWidth: 1))
                
                Menu {
                    Button("High") { priority = "High" }
                    Button("Medium") { priority = "Medium" }
                    Button("Low") { priority = "Low" }
                } label: {
                    HStack {
                        Text(priority)
                        Spacer()
                        Image(systemName: "chevron.down")
                            .foregroundColor(.blue)
                    }
                    .padding()
                    .overlay(RoundedRectangle(cornerRadius: 8).stroke(.gray, lineWidth: 1))
                }
                
                Menu {
                    Button("Work") { category = "Work" }
                    Button("Personal") { category = "Personal" }
                    Button("Other") { category = "Other" }
                } label: {
                    HStack {
                        Text(category)
                        Spacer()
                        Image(systemName: "chevron.down")
                            .foregroundColor(.blue)
                    }
                    .padding()
                    .overlay(RoundedRectangle(cornerRadius: 8).stroke(.gray, lineWidth: 1))
                }
                
                TextField("Description", text: $description)
                    .padding()
                    .overlay(RoundedRectangle(cornerRadius: 8).stroke(.gray, lineWidth: 1))
                
                Button(action: {
                    isDatePickerVisible.toggle()
                }) {
                    HStack {
                        Text("Due date: \(dueDate, formatter: DateFormatter.shortDateFormatter)")
                        Spacer()
                        Image(systemName: "calendar")
                            .foregroundColor(.blue)
                    }
                    .padding()
                    .overlay(RoundedRectangle(cornerRadius: 8).stroke(.gray, lineWidth: 1))
                }
                .sheet(isPresented: $isDatePickerVisible) {
                    VStack {
                        DatePicker("Select Due Date", selection: $dueDate, displayedComponents: .date)
                            .datePickerStyle(WheelDatePickerStyle())
                            .labelsHidden()
                        
                        Button("Done") {
                            isDatePickerVisible = false
                        }
                        .padding()
                        .frame(maxWidth: .infinity)
                        .background(Color.blue)
                        .foregroundColor(.white)
                        .cornerRadius(8)
                        .padding(.horizontal)
                    }
                    .presentationDetents([.medium])
                }
                
                Toggle(isOn: $notificationsEnabled) {
                    VStack(alignment: .leading) {
                        Text("Notifications")
                            .foregroundColor(.white)
                        Text("Receive notifications when the task is due")
                            .font(.subheadline)
                            .foregroundColor(.gray)
                    }
                }
                .padding()
                
                Spacer()
                
                Button(action: {
                    // Action to create the task
                }) {
                    Text("Create task")
                        .font(.title2)
                        .bold()
                        .foregroundColor(.white)
                        .frame(maxWidth: .infinity)
                        .padding()
                        .background(Color.blue)
                        .cornerRadius(8)
                }
            }
            .padding()
            .edgesIgnoringSafeArea(.all)
        }
    }
}

extension DateFormatter {
    static var shortDateFormatter: DateFormatter {
        let formatter = DateFormatter()
        formatter.dateFormat = "dd/MM/yyyy"
        return formatter
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

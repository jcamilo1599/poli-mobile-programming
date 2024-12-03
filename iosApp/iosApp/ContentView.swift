import SwiftUI
import Shared

struct ContentView: View {
    @State private var title: String = ""
    @State private var priority: String = "Seleccionar Prioridad"
    @State private var category: String = "Seleccionar Categoría"
    @State private var description: String = ""
    @State private var dueDate = Date()
    @State private var isDatePickerVisible: Bool = false
    @State private var notificationsEnabled: Bool = true
    
    var body: some View {
        ScrollView {
            VStack(spacing: 20) {
                TextField("Titulo", text: $title)
                    .padding()
                    .overlay(RoundedRectangle(cornerRadius: 8).stroke(.gray, lineWidth: 1))
                
                Menu {
                    Button("Alta") { priority = "Alta" }
                    Button("Media") { priority = "Media" }
                    Button("Baja") { priority = "Baja" }
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
                    Button("Trabajo") { category = "Trabajo" }
                    Button("Personal") { category = "Personal" }
                    Button("Otra") { category = "Otra" }
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
                
                TextField("Descripción", text: $description)
                    .padding()
                    .overlay(RoundedRectangle(cornerRadius: 8).stroke(.gray, lineWidth: 1))
                
                Button(action: {
                    isDatePickerVisible.toggle()
                }) {
                    HStack {
                        Text("Fecha de vencimiento: \(dueDate, formatter: DateFormatter.shortDateFormatter)")
                        Spacer()
                        Image(systemName: "calendar")
                            .foregroundColor(.blue)
                    }
                    .padding()
                    .overlay(RoundedRectangle(cornerRadius: 8).stroke(.gray, lineWidth: 1))
                }
                .sheet(isPresented: $isDatePickerVisible) {
                    VStack {
                        DatePicker("Seleccionar fecha", selection: $dueDate, displayedComponents: .date)
                            .datePickerStyle(WheelDatePickerStyle())
                            .labelsHidden()
                        
                        Button("Listo") {
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
                        Text("Notificaciones")
                            .foregroundColor(.white)
                        Text("Recibir notificaciones cuando la tarea esté vencida")
                            .font(.subheadline)
                            .foregroundColor(.gray)
                    }
                }
                .padding()
                
                Spacer()
                
                Button(action: {
                    // Action to create the task
                }) {
                    Text("Crear tarea")
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

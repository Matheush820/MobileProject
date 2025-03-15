import { StyleSheet } from 'react-native';

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 20,
  },
  topSection: {
    alignItems: 'center',
    marginBottom: 20,
  },
  logoContainer: {
    alignItems: 'center',
  },
  logoImage: {
    width: 100,
    height: 100,
    marginBottom: 10,
  },
  logoText: {
    fontSize: 24,
    fontWeight: 'bold',
    color: '#fff',
  },
  logoSubText: {
    fontSize: 16,
    color: '#ccc',
  },
  inputContainer: {
    marginBottom: 20,
  },
  input: {
    height: 50,
    borderColor: '#ccc',
    borderWidth: 1,
    borderRadius: 8,
    marginBottom: 15,
    paddingHorizontal: 10,
    color: '#000',
    backgroundColor: '#fff',
  },
  passwordContainer: {
    flexDirection: 'row',
    alignItems: 'center',
    borderColor: '#ccc',
    borderWidth: 1,
    borderRadius: 8,
    marginBottom: 15,
    paddingHorizontal: 10,
    backgroundColor: '#fff',
  },
  inputPassword: {
    flex: 1,
    height: 50,
    color: '#000',
  },
  errorText: {
    color: 'red',
    textAlign: 'center',
    marginBottom: 10,
  },
  buttonContainer: {
    alignItems: 'center',
  },
  loginButton: {
    backgroundColor: '#00C896',
    borderRadius: 8,
    paddingVertical: 15,
    paddingHorizontal: 40,
  },
  loginButtonText: {
    color: '#fff',
    fontSize: 18,
    fontWeight: 'bold',
  },
});

export default styles;

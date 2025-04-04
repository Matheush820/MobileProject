import { StyleSheet } from 'react-native'; 

export default StyleSheet.create({
  container: {
    flex: 1,
  },
  topSection: {
    height: '40%',
    borderBottomLeftRadius: 40,
    borderBottomRightRadius: 40,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F7F1F1', // Cor de fundo fixa para evitar erro de gradiente
    padding: 20,
    elevation: 6, // Sombra para Android
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 4 },
    shadowOpacity: 0.2,
    shadowRadius: 6,
  },
  
  logoContainer: {
    alignItems: 'center',
    transform: [{ scale: 1.2 }],
    opacity: 0.9,
  },
  logoImage: {
    width: 120,
    height: 120,
    borderRadius: 60,
    marginBottom: 12,
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 6 },
    shadowOpacity: 0.15,
    shadowRadius: 10,
    borderWidth: 3,
    borderColor: '#fff',
  },
  logoText: {
    fontSize: 36,
    fontWeight: 'bold',
    color: '#1D4C77',
    letterSpacing: 3,
    marginBottom: 4,
    textTransform: 'uppercase',
  },
  logoSubText: {
    fontSize: 22,
    color: '#1D4C77',
    fontStyle: 'italic',
    transform: [{ scale: 1.05 }],
  },
  inputContainer: {
    marginTop: 30,
    paddingHorizontal: 25,
  },
  input: {
    height: 50,
    backgroundColor: '#FFF',
    borderRadius: 10,
    marginBottom: 15,
    paddingHorizontal: 15,
    fontSize: 16,
    color: '#333',
  },
  passwordContainer: {
    flexDirection: 'row',
    alignItems: 'center',
    backgroundColor: '#FFF',
    borderRadius: 10,
    paddingHorizontal: 15,
    height: 50,
    marginBottom: 5, // Reduzi o espaçamento antes do "Esqueceu a senha?"
  },
  inputPassword: {
    flex: 1,
    fontSize: 16,
    color: '#333',
  },
  forgotPassword: {
    marginTop: 10,
    color: '#FFF',
    fontSize: 14,
    textDecorationLine: 'underline',
    textAlign: 'left', 
    alignSelf: 'flex-start', 
    marginLeft: 25, 
  },
  
  buttonContainer: {
    justifyContent: 'center',
    marginTop: 20,
    paddingHorizontal: 20,
  },
  loginButton: {
    backgroundColor: '#1D4C77',
    borderRadius: 30,
    height: 50,
    justifyContent: 'center',
    alignItems: 'center',
    marginRight: 10,
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 4 },
    shadowOpacity: 0.3,
    shadowRadius: 6,
    elevation: 5,
  },
  loginButtonText: {
    color: '#FFF',
    fontSize: 18,
    fontWeight: 'bold',
  },
  signUpLink: {
    marginTop: 23,
    color: '#FFF',
    fontSize: 14,
    textDecorationLine: 'underline',
    textAlign: 'center',
  },
  footerText: {
    marginTop: 110,
    color: '#FFF',
    fontSize: 12,
    textAlign: 'center',
  },
});

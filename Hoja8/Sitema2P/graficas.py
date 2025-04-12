import matplotlib.pyplot as plt

def graficar_espera(tiempos):
    plt.plot(tiempos, marker='o')
    plt.title("Tiempo de espera por paciente")
    plt.xlabel("Paciente")
    plt.ylabel("Minutos")
    plt.grid(True)
    plt.savefig("tiempos_espera.png")
    plt.show()
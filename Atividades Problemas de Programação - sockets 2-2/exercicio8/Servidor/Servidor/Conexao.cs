using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Sockets;
using System.Net;
using System.Text;
using System.Threading.Tasks;

namespace Servidor
{
    public class Conexao : BaseThread
    {
        public readonly string MsgBadRqt = "400 Bad Request";

        Socket SocketCliente;

        public Conexao(Socket aSocketCliente)
        {
            SocketCliente = aSocketCliente;
        }


        public override void RunThread()
        {
            bool erroRqt;
            string? msgErro;

            StreamWriter? saida;
            StreamReader? entrada;

            IPAddress endCliente = ((IPEndPoint)SocketCliente?.RemoteEndPoint)?.Address;

            string? salarioMedio; ;

            try
            {
                NetworkStream stream = new(SocketCliente!);
                saida = new StreamWriter(stream, leaveOpen: true);
                entrada = new StreamReader(stream);

                salarioMedio = entrada.ReadLine();

                double sm = double.Parse(salarioMedio!);

                if (sm >= 0 && sm <= 200) saida.WriteLine("Saldo medio: " + sm + "Crédito: " + "sem direito.");
                else if (sm >= 201 && sm <= 400) saida.WriteLine("Saldo medio: " + sm + "Crédito: " + sm * 0.2);
                else if (sm >= 401 && sm <= 600) saida.WriteLine("Saldo medio: " + sm + "Crédito: " + sm * 0.3);
                else if (sm >= 601) saida.WriteLine("Saldo medio: " + sm + "Crédito: " + sm * 0.4);

                else
                {
                    erroRqt = true;
                    msgErro = MsgBadRqt;
                    saida.WriteLine("Erro ...!");
                }

                SocketCliente!.Close() ;
                saida.Close();
                entrada.Close();
            }
            catch(IOException e)
            {
                Console.WriteLine("Erro E/S " + e);
            }
        }
    }
}

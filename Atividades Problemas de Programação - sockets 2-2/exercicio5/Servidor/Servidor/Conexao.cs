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

            string? idade;

            try
            {
                NetworkStream stream = new(SocketCliente!);
                saida = new StreamWriter(stream, leaveOpen: true);
                entrada = new StreamReader(stream);

                idade = entrada.ReadLine();

                int id = Int32.Parse(idade!);

                if (id > 18) saida.WriteLine("Adulto.");

                else if (id >= 14 && id <= 17) saida.WriteLine("Juvenil B");

                else if (id >= 11 && id <= 13) saida.WriteLine("Juvenil A");

                else if (id >= 8 && id <= 10) saida.WriteLine("Infantil B");

                else if (id >= 5 && id <= 7) saida.WriteLine("Infantil A");

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

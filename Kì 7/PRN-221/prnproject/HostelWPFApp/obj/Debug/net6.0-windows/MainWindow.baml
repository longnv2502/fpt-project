   M S B A M L   `   `   `  ����G  CHostelWPFApp, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null+clr-namespace:HostelWPFAppHostelWPFApp  5   	       HostelWPFApp.MainWindow   5   	   W SPresentationCore, Version=6.0.2.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35\ XPresentationFramework, Version=6.0.2.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35e aSystem.Windows.Controls.Ribbon, Version=6.0.2.0, Culture=neutral, PublicKeyToken=b77a5c561934e089R NWindowsBase, Version=6.0.2.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35F 9http://schemas.microsoft.com/winfx/2006/xaml/presentation     5   	   R NSystem.Xaml, Version=6.0.2.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5   	   @d2http://schemas.microsoft.com/expression/blend/2008     5   	   Jmc;http://schemas.openxmlformats.org/markup-compatibility/2006     5   	   ,localclr-namespace:HostelWPFApp     5   	   _ [MaterialDesignThemes.Wpf, Version=4.7.1.0, Culture=neutral, PublicKeyToken=df2a72020bd7962aFmaterialDesign1http://materialdesigninxaml.net/winfx/xaml/themes  5   	   -   5          � Title$  
MainWindow��5	   	   $	��910��6   $
��1399��6)    � WindowStartupLocation$ CenterScreen=�66    � WindowStyle$
 None=�6[    � 
ResizeMode$ NoResize=�6n    � AllowsTransparency$
 True��6�   ��6�   Z� .��5
      � .+�5   
   )  #MaterialDesignThemes.Wpf.DialogHost     
Identifier$ 
RootDialog��6$    K� ZIndex$	 999��5   $   $��Center=�5   $   $��Center=�5   $      CloseOnClickAway$
 True��5   $      DialogTheme$ Inherit=�5   $   5      #  MaterialDesignThemes.Wpf.Card  5   
   $��10q�6   	   UniformCornerRadius$	 30Q�6*   .��5      � 
 � ColumnDefinitions
 5      �� 5      $��90 �6'   64   �� 5      $��* �6'   63    � 	Resources 5      '  !MaterialDesignThemes.Wpf.PackIcon �� 
TargetType �� Property �� Value��%�   '        �� 5      "  6   .�5      ��  � ��$ 30��62   6?   �� 5       � ��$ 30��63   6@   5      5      .+�5      � $��0��6   .+�5      �� $
��0 15q�6   .@�5       �� $��Center=�6'   $��	0 40 0 10q�6D   .��5!      �� -   5         ��Name$ btnContract��5!   %    &  "MaterialDesignFloatingActionButton#��[  6:   $
��Left=�6v   
���   ���5"   $   Z� ��6;   Z� .��5#   "   �� $��-5q�6-   .+�5$   &        Kind$ Notebook=�6>   6P   5%   #   5&      5'      �� 5(      $��Center=�6'   $��0 q�6D   .��5)      �� -   5        $ btnCustomer��5)   %   #��[  6:   $
��Left=�6v   
���   ���5*   $   Z� ��6;   Z� .��5+   "   �� $��-5q�6-   .+�5,   &     $ Account=�6>   6O   5-   #   5.      5/      �� 50      $��Center=�6'   $
��0 10q�6D   .��51      �� -   5        $ btnRoom��51   %   #��[  66   $
��Left=�6r   
���   ���52   $   Z� ��6;   Z� .��53   "   �� $��-5q�6-   .+�54   &     $ ScaleBathroom=�6>   6U   55   #   56      57      �� 58      $��Center=�6'   $��0 q�6D   .��59      �� -   5        $ btnBill��59   %   #��[  66   $
��Left=�6r   
���   ���5:   $   Z� ��6;   Z� .��5;   "   �� $��-5q�6-   .+�5<   &     $ 	Newspaper=�6>   6Q   5=   #   5>      5?      �� 5@      $��Center=�6'   $
��0 10q�6D   .��5A      �� -   5        $ 
btnService��5A   %   #��[  69   $
��Left=�6u   
���   ���5B   $   Z� ��6;   Z� .��5C   "   �� $��-5q�6-   .+�5D   &     $ EqualiserVertical=�6>   6Y   5E   #   5F      5G      �� 5H      $��Center=�6'   $��0 60 0 0q�6D   .��5I      �� -   5        $ btnExit��5J   d   #��[  5I   %   $
��Left=�6a   
���   ���5J   $   Z� ��6;   Z� .��5K   "   �� $��-5q�6-   .+�5L   &     $ 	ExitToApp=�6>   6Q   5M   #   5N      5O      5P      5Q      � 5R      -   5        $��RenderPages��5R   <   $��1��6   ���#F6F5F85T      5U      5V      5W      5X      
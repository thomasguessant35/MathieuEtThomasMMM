    package com.example.mathieuetthomas;

    import android.content.Context;
    import android.net.Uri;
    import android.os.Bundle;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.TextView;
    import androidx.fragment.app.Fragment;
    import butterknife.BindView;

    public class Display extends Fragment {

        private OnDisplayInteractionListener mListener;

    public Display () {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_display, container, false);
        if (getArguments() != null) {
            TextView nomSaisi = view.findViewById(R.id.displayNom);
            TextView prenomSaisi = view.findViewById(R.id.displayPrenom);
            TextView dateSaisie = view.findViewById(R.id.displayDateNaissance);
            TextView villeSaisie = view.findViewById(R.id.displayVilleNaissance);
            nomSaisi.setText(getArguments().getString("nomSaisi"));
            prenomSaisi.setText(getArguments().getString("prenomSaisi"));
            dateSaisie.setText(getArguments().getString("dateSaisie"));
            villeSaisie.setText(getArguments().getString("villeSaisie"));
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnDisplayInteractionListener) {
            mListener = (OnDisplayInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnDisplayInteractionListener {
        // TODO: Update argument type and name
        void onDisplayInteraction(Uri uri);
    }
}

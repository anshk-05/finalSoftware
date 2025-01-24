/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{js,jsx,ts,tsx}", // Scans all files in your `src` directory for Tailwind classes
  ],
  theme: {
    extend: {
      colors: {
        // Pokémon-inspired colors
        pokeRed: "#FF0000",
        pokeYellow: "#FFCC00",
        pokeBlue: "#3B4CCA",
        pokeGreen: "#48D1CC",
        pokeBlack: "#2C2C2C",
        pokeWhite: "#FFFFFF",
      },
      fontFamily: {
        // Pokémon-inspired font
        pokemon: ["'Press Start 2P'", "sans-serif"], // Add a fun Google Font
      },
      backgroundImage: {
        // Add optional Pokémon-themed background patterns
        //pokeball: "url('/assets/pokeball.png')", // Replace with actual image path in your `public` folder or `src/assets`
        //pikachu: "url('/assets/pikachu.png')", // Replace with an actual image path
      },
    },
  },
  plugins: [],
};
